plugins {
    `java-library`
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(libs.arrow.core)
    implementation(libs.kotlinx.datetime)

    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
}