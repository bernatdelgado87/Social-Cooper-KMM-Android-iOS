plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id ("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
}

android {
    namespace = "app.mistercooper.ui.common"
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
    buildFeatures {
        compose = true
        buildConfig = true
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
}
    allprojects {
    dependencies {

        implementation(project(":domain:domain-common"))
        api(libs.androidx.core.ktx)
        api(libs.androidx.appcompat)
        api(libs.material)
        //remote image
        api(libs.coil)
        //hilt ui
        implementation(libs.dagger.hilt.android)
        api(libs.dagger.hilt.android.compose)
        api(libs.dagger.hilt.android.navigation)
        ksp (libs.dagger.hilt.android.compiler)
        ksp (libs.dagger.hilt.compiler)

        //todo check this
        api(libs.androidx.activity.compose)
        //
        api(platform(libs.androidx.compose.bom))
        api(libs.androidx.ui)
        api(libs.androidx.ui.graphics)
        api(libs.androidx.ui.tooling.preview)
        api(libs.androidx.material3)

        //lottie
        implementation(libs.lottie)

        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)
    }
}