import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.itzephir.whererubles.feature.categories"
    compileSdk = libs.versions.android.compile.sdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.min.sdk.get().toInt()
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    implementation(projects.core.di)
    implementation(projects.core.format)
    implementation(projects.core.ui)
    implementation(projects.core.ui.theme)

    implementation(projects.feature.categories.domain)
    implementation(projects.feature.categories.data)

    implementation(libs.kotlinx.datetime)

    implementation(libs.arrow.core)

    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)

    implementation(libs.androidx.material3)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.respawn.flowmvi.core)
    implementation(libs.respawn.flowmvi.compose)
    implementation(libs.respawn.flowmvi.android)
    implementation(libs.respawn.flowmvi.savedstate)

    implementation(libs.androidx.lifecycle.viewmodel.compose)

    implementation(libs.dagger)
    ksp(libs.dagger.compiler)

    implementation(libs.ktor.client.core)
}
