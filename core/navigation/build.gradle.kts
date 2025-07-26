import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.itzephir.whererubles.core.navigation"
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
    implementation(projects.core.data.common)
    implementation(projects.core.data)
    implementation(projects.core.data.account)
    implementation(projects.core.data.category)
    implementation(projects.core.data.transaction)
    implementation(projects.core.storage)
    implementation(projects.core.storage.account)
    implementation(projects.core.storage.category)
    implementation(projects.core.storage.transaction)
    implementation(projects.core.network)
    implementation(projects.core.network.account)
    implementation(projects.core.network.category)
    implementation(projects.core.network.transaction)
    implementation(projects.feature.expenses)
    implementation(projects.feature.income)
    implementation(projects.feature.account)
    implementation(projects.feature.categories)
    implementation(projects.feature.settings)
    implementation(projects.feature.transactionEditor)

    implementation(projects.core.ui.theme)

    implementation(libs.androidx.datastore)
    implementation(libs.androidx.datastore.preferences)

    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)

    implementation(libs.androidx.material3)

    implementation(libs.androidx.navigation.compose)

    implementation(libs.kotlinx.serialization.core)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.dagger)
    ksp(libs.dagger.compiler)

    implementation(libs.ktor.client.core)
    implementation(libs.androidx.room.runtime)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
