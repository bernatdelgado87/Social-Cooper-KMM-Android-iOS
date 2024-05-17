plugins {
    id("java-library")
    alias(libs.plugins.jetbrainsKotlinJvm)
    id("com.google.devtools.ksp")
    kotlin("plugin.serialization")

}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    api(project(":domain:domain-common"))
    //Hilt Domain
    implementation(libs.dagger.hilt.core)
    ksp(libs.dagger.hilt.core.compiler)
}