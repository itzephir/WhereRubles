import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.mappie)
}

android {
    namespace = "com.itzephir.whererubles.app"
    compileSdk = libs.versions.android.compile.sdk.get().toInt()

    defaultConfig {
        applicationId = "com.itzephir.whererubles"
        minSdk = libs.versions.android.min.sdk.get().toInt()
        targetSdk = libs.versions.android.target.sdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        run {
            val properties = Properties()
            properties.load(project.rootProject.file("local.properties").inputStream())
            val apiKey = properties.getProperty("api.key") as String
            buildConfigField(type = "String", name = "apiKey", value = apiKey)
        }

        buildConfigField(
            type = "String",
            name = "apiBaseUrl",
            value = "\"${properties["api.baseUrl"]!! as String}\""
        )
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
        buildConfig = true
    }
}

kotlin{
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    implementation(projects.core.di)

    implementation(projects.core.ui.theme)

    implementation(projects.core.sync)
    implementation(projects.core.storage)
    implementation(projects.core.storage.common)
    implementation(projects.core.storage.account)
    implementation(projects.core.storage.category)
    implementation(projects.core.storage.transaction)
    implementation(projects.core.network)
    implementation(projects.core.network.common)
    implementation(projects.core.network.account)
    implementation(projects.core.network.category)
    implementation(projects.core.network.transaction)
    implementation(projects.core.data)
    implementation(projects.core.data.account)
    implementation(projects.core.data.category)
    implementation(projects.core.data.transaction)
    implementation(projects.core.data.common)
    implementation(projects.core.connection)
    implementation(projects.core.navigation)

    implementation(libs.arrow.core)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)

    implementation(libs.androidx.lifecycle.runtime)

    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)

    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons.extended)

    implementation(libs.androidx.navigation.compose)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.dagger)
    ksp(libs.dagger.compiler)

    implementation(libs.ktor.client.core)

    implementation(libs.androidx.work.runtime.ktx)

    implementation(libs.androidx.datastore)
    implementation(libs.androidx.datastore.preferences)

    implementation(libs.mappie.api)
}