// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.12.2" apply false
    id("org.jetbrains.kotlin.android") version "2.2.10" apply false
    id("convention.detekt")
    id("com.google.devtools.ksp") version "2.2.10-2.0.2" apply false
    alias(libs.plugins.compose.compiler) apply false
}
