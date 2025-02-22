plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id ("dagger.hilt.android.plugin")
    id ("com.google.devtools.ksp")
}

android {
    namespace = "com.juanzurita.framework"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        buildConfig = true
        aidl = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }


}

dependencies {
    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":data"))
    //hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    ksp(libs.hilt.compiler)
    // Retrofit
    implementation(libs.retrofit.gson.moshi)
    implementation (libs.squareup.moshi.kotlin)
    ksp(libs.moshi.kotlin.codegen)
    implementation(libs.gson)
    // OkHttp3
    implementation(libs.okhttp3)
    implementation(libs.okhttp3.loggingInterceptor)
    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
}