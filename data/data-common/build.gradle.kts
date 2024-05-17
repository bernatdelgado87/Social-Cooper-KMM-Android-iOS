plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id ("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
    kotlin("plugin.serialization")
}

android {
    namespace = "app.mistercooper.data.common"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    implementation(project(":domain:domain-common"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    //log interceptor
    api (libs.okhttp.logging)

    //Retrofit
    api (libs.retrofit)
    api (libs.retrofit.gson)
    //Serialization
    api(libs.kotlin.serialization)

    //Hilt
    implementation(libs.dagger.hilt.android)
    ksp (libs.dagger.hilt.android.compiler)
    ksp (libs.dagger.hilt.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}