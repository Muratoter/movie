plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = Configs.applicationId
    compileSdk = Configs.compileSdk

    defaultConfig {
        applicationId = Configs.applicationId
        minSdk = Configs.minSdk
        targetSdk = Configs.targetSdk
        versionCode = Configs.versionCode
        versionName = getVersionName()

        testInstrumentationRunner = Configs.testInstrumentationRunner
    }

    buildTypes {
        getByName("release") {
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
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":ui"))

    // UI
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.material)

    // Navigation
    implementation(Dependencies.navigation)
    implementation(Dependencies.navigationKtx)

    // Dependency Injection
    implementation(Dependencies.daggerHilt)
    kapt(Dependencies.daggerHiltCompiler)

    // Log
    implementation(Dependencies.timber)


}

fun getVersionName(): String {
    return "${Configs.MAJOR}.${Configs.MINOR}.${Configs.PATCH}"
}