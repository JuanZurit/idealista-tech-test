plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id ("dagger.hilt.android.plugin")
    id ("com.google.devtools.ksp")
}

android {
    namespace = "com.juanzurita.core"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    //hilt
    api(libs.hilt.android)
    api(libs.androidx.viewbinding)
    ksp(libs.hilt.android.compiler)
    ksp(libs.hilt.compiler)
    // Retrofit
    implementation(libs.retrofit)
    // DataStore
    implementation(libs.data.store)
    // Arrow
    api(libs.arrow)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}