plugins {
    `java-library`
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(projects.core.network.account)

    implementation(libs.arrow.core)

    implementation(libs.ktor.client.core)

    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
}