plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.fatmawati.countryinfoapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.fatmawati.countryinfoapp"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Core Android UI
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation("androidx.constraintlayout:constraintlayout:2.1.4") // Seringkali dibutuhkan untuk layout

    // Retrofit & Gson (untuk koneksi API dan parsing JSON)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // OkHttp Logging Interceptor (untuk melihat log request/response di Logcat, sangat berguna saat debug)
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    // Coroutines (untuk operasi asinkron yang efisien)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1") // Pastikan versi terbaru yang stabil
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1") // Untuk Coroutines di Android

    // ViewModel & LiveData (untuk arsitektur MVVM dan manajemen siklus hidup)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1") // ViewModel untuk Coroutine
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")   // LiveData
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation(libs.androidx.activity)   // lifecycleScope

    implementation("androidx.cardview:cardview:1.0.0")
    // Testing (sudah ada, hanya sebagai referensi)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}