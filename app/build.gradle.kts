plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id ("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
    kotlin("plugin.serialization")
}

android {
    namespace = "app.mistercooper.social"
    compileSdk = 34

    defaultConfig {
        applicationId = "app.mistercooper.social"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.12"
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":ui:ui-common"))
    implementation(project(":ui:ui-home"))
    implementation(project(":ui:ui-publish"))
    implementation(project(":ui:ui-registerLogin"))
    implementation(project(":ui:ui-comment"))
    implementation(project(":data:data-main"))
    implementation(project(":data:data-comment"))
    implementation(project(":data:data-common"))
    implementation(project(":data:data-home"))
    implementation(project(":data:data-publish"))
    implementation(project(":data:data-register-login"))
    implementation(project(":domain:domain-comment"))
    implementation(project(":domain:domain-common"))
    implementation(project(":domain:domain-home"))
    implementation(project(":domain:domain-publish"))
    implementation(project(":domain:domain-register-login"))

    implementation(libs.androidx.lifecycle.runtime.ktx)
    //Hilt
    implementation(libs.dagger.hilt.android)
    implementation(libs.dagger.hilt.android.compose)
    implementation(libs.dagger.hilt.android.navigation)
    ksp (libs.dagger.hilt.android.compiler)
    ksp (libs.dagger.hilt.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}