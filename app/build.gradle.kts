@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    kotlin("kapt")
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "com.kosmasfn.weather"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.kosmasfn.weather"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    kapt {
        correctErrorTypes = true
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(mapOf("path" to ":utils")))
    implementation(project(mapOf("path" to ":core")))
    implementation(project(mapOf("path" to ":domain")))

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)

    implementation(libs.activity)
    implementation(libs.fragment)

    //Hilt
    implementation(libs.dagger.hilt)
    kapt(libs.hilt.android.compiler)

    //Networking
    implementation(libs.retrofit)
    implementation(libs.retrofit.moshi)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp)

    //Coroutines
    implementation(libs.coroutines)

    //ViewModel
    implementation(libs.viewmodel)
    implementation(libs.viewmodel.runtime)
    implementation(libs.viewmodel.savedstate)

    //Util
    implementation(libs.glide)
    ksp(libs.glide.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}