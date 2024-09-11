// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false

    id ("com.google.dagger.hilt.android") version "+" apply false
    id ("org.jetbrains.kotlin.kapt") version "+" apply false
}