plugins {
    `java-library`
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(projects.core.model)

    implementation(libs.arrow.core)
    implementation(libs.kotlinx.datetime)
    implementation(libs.dagger)
}