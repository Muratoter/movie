import java.io.FileInputStream
import java.io.FileNotFoundException
import java.util.Properties

fun getApiKey(): String {
    val fl = rootProject.file("apikey.properties")

    if (fl.exists()) {
        val properties = Properties()
        properties.load(FileInputStream(fl))
        return properties.getProperty("API_KEY")
    } else {
        throw FileNotFoundException()
    }
}

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.moter.tmdb.data"
    compileSdk = Configs.compileSdk

    defaultConfig {
        minSdk = Configs.minSdk
        targetSdk = Configs.targetSdk

        testInstrumentationRunner = Configs.testInstrumentationRunner
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "API_BASE_URL", "\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "API_KEY", getApiKey())
        }
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "API_BASE_URL", "\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "API_KEY", getApiKey())
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
    implementation(project(":core"))

    // Log
    implementation(Dependencies.timber)

    // OKHttp Client
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitGsonConverter)
    implementation(Dependencies.okHttp)
    implementation(Dependencies.okHttpInterceptor)

    // Dependency Injection
    implementation(Dependencies.daggerHilt)
    kapt(Dependencies.daggerHiltCompiler)

    // Testing
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.junitExt)
    androidTestImplementation(Dependencies.espresso)
}