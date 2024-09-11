plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    id ("kotlin-kapt")
    id ("com.google.dagger.hilt.android")
}

android {
    namespace = "com.amrk000.yajhaz"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.amrk000.yajhaz"
        minSdk = 26
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures{
        viewBinding = true
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //MVVM
    // ViewModel:
    implementation (libs.androidx.lifecycle.viewmodel)
    // Saved state module:
    implementation (libs.androidx.lifecycle.viewmodel.savedstate)
    // LiveData:
    implementation (libs.androidx.lifecycle.livedata)

    //Navigation Component
    implementation (libs.androidx.navigation.fragment.v241)
    implementation (libs.androidx.navigation.ui.v241)

    //Jetpack Splash Screen
    implementation (libs.androidx.core.splashscreen)

    //Retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)

    //Kotlin coroutines
    implementation(libs.kotlinx.coroutines.android)

    //Glide
    implementation (libs.glide)
    annotationProcessor (libs.compiler)

    //Dagger Hilt
    implementation(libs.hilt.android)
    kapt (libs.hilt.compiler)
    //Dagger Hilt testing
    testImplementation (libs.hilt.android.testing)
    kaptTest (libs.hilt.android.compiler)
    //Dagger Hilt testing
    androidTestImplementation (libs.hilt.android.testing)
    kaptAndroidTest (libs.hilt.android.compiler)
}
