import io.gitlab.arturbosch.detekt.Detekt

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
    alias(libs.plugins.detekt)
}

subprojects {
    afterEvaluate {
        apply {
            plugin(libs.plugins.detekt.get().pluginId)
        }

        detekt {
            toolVersion = libs.versions.detekt.get()
            buildUponDefaultConfig = true
        }

        tasks.withType<Detekt>().configureEach {
            jvmTarget = "17"
            reports {
                xml.required.set(true)
                html.required.set(true)
                sarif.required.set(true)
                md.required.set(true)
            }
        }
    }
}

detekt {
    config.setFrom(file("config/detekt/detekt.yml"))
}

tasks.withType<Detekt>().configureEach {
    jvmTarget = "17"
    reports {
        xml.required.set(true)
        html.required.set(true)
        sarif.required.set(true)
        md.required.set(true)
    }
}