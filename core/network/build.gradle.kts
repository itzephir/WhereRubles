import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    jvm()

    sourceSets {
        androidMain.dependencies {
            implementation(libs.dagger)
        }

        commonMain.dependencies {
            implementation(projects.core.model)

            implementation(projects.core.network.account)
            implementation(projects.core.network.category)
            implementation(projects.core.network.transaction)

            implementation(libs.bundles.ktor.client)
            implementation(libs.arrow.core)
            implementation(libs.arrow.resilience)
            implementation(libs.arrow.resilience.ktor.client)

            implementation(libs.kotlinx.serialization.core)
        }
    }
}

android {
    namespace = "com.itzephir.whererubles.core.network"
    compileSdk = libs.versions.android.compile.sdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = libs.versions.android.min.sdk.get().toInt()
    }
}