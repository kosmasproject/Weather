package com.kosmasfn.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Kosmas on October 11, 2023
 */
data class BaseResponse<T>(
    @SerializedName("data") val data: T,
    @SerializedName("error") val errors: String?,
    @SerializedName("message") val message: String?,
    @SerializedName("code") val responseCode: Int?
)

data class BaseResponseWithoutData(
    @SerializedName("error") val errors: String?,
    @SerializedName("message") val message: String?,
    @SerializedName("code") val responseCode: Int?
)