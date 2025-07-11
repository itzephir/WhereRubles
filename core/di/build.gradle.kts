plugins {
    `java-library`
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.google.ksp)
}

dependencies {
    implementation(libs.ktor.client.core)

    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
}

