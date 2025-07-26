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
        commonMain.dependencies {
            implementation(libs.dagger)
        }
        androidMain.dependencies {
            implementation(libs.androidx.core.ktx)

            implementation(projects.core.data.common)
            implementation(projects.core.data.account)
            implementation(projects.core.data.category)
            implementation(projects.core.data.transaction)

            implementation(libs.androidx.work.runtime.ktx)
        }
    }
}

android {
    namespace = "com.itzephir.whererubles.core.sync"
    compileSdk = libs.versions.android.compile.sdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = libs.versions.android.min.sdk.get().toInt()
    }
}