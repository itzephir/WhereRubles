import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinCompileCommon

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
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.room) apply false
    alias(libs.plugins.mappie) apply false
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

        tasks.withType<KotlinCompileCommon>().configureEach {
            compilerOptions{
                freeCompilerArgs.addAll("-opt-in=kotlin.time.ExperimentalTime")
            }
        }

        tasks.withType<KotlinCompile>().configureEach {
            compilerOptions {
                freeCompilerArgs.addAll("-opt-in=kotlin.time.ExperimentalTime")
            }
        }

        tasks.withType<KotlinCompile>().configureEach {
            compilerOptions {
                freeCompilerArgs.add("-Xcontext-parameters")
            }
        }

        tasks.withType<KotlinCompileCommon>().configureEach {
            compilerOptions {
                freeCompilerArgs.add("-Xcontext-parameters")
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