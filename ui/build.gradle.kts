plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.moter.tmdb.ui"
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
            buildConfigField("String", "API_CONTENT_URL", "\"https://image.tmdb.org/t/p/w500/\"")
        }
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "API_CONTENT_URL", "\"https://image.tmdb.org/t/p/w500/\"")
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
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":core"))

    // Log
    implementation(Dependencies.timber)

    // UI
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.constraintLayout)
    implementation(Dependencies.dotsIndicator)
    implementation(Dependencies.swipeToRefresh)

    // ViewModel
    implementation(Dependencies.viewModelKtx)

    // Dependency Injection
    implementation(Dependencies.daggerHilt)
    kapt(Dependencies.daggerHiltCompiler)

    // Navigation
    implementation(Dependencies.navigation)
    implementation(Dependencies.navigationKtx)

    // Paging
    implementation(Dependencies.paging)

    // Image Loader
    implementation(Dependencies.glide)
    implementation(Dependencies.glideAnnotationProcessor)
    // Testing
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.junitExt)
    androidTestImplementation(Dependencies.espresso)
}