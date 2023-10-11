package com.kosmasfn.data.network.interceptor

import com.google.gson.Gson
import com.kosmasfn.data.BuildConfig
import com.kosmasfn.data.model.BaseResponse
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import javax.inject.Inject

/**
 * Created by Kosmas on October 11, 2023
 */
class AuthInterceptor @Inject constructor(
    private val gson: Gson
) : Interceptor {

    companion object {
        const val KEY_HEADER_ACCEPT = "Accept"
        const val KEY_HEADER_X_USER_AGENT = "x-user-agent"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .addHeader("Authorization", BuildConfig.WEATHER_API_KEY)
            .addHeader(KEY_HEADER_ACCEPT, "application/json")
            .method(originalRequest.method, originalRequest.body)
        requestBuilder.addHeader(KEY_HEADER_X_USER_AGENT, "Android")

        val newRequest = requestBuilder.build()
        return try {
            chain.proceed(newRequest)
        } catch (e: Exception) {
            val error = BaseResponse<Any?>(null, "${e.message}", "${e.message}", 500)
            val response = Response.Builder()
                .protocol(Protocol.HTTP_1_1)
                .message(("Network Exception Error : " + e.message) ?: "-")
                .request(newRequest)
                .code(500)
                .body(
                    ResponseBody.create(
                        "application/json".toMediaTypeOrNull(),
                        gson.toJson(error)
                    )
                )
            response.build()
        }
    }
}
