plugins {
    `java-library`
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ksp)
}

dependencies {
    api(projects.core.model)
    api(projects.core.data.common)
    api(projects.core.data.account)

    implementation(projects.feature.account.domain)

    implementation(libs.kotlinx.datetime)

    implementation(libs.arrow.core)
    implementation(libs.ktor.client.core)

    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
}