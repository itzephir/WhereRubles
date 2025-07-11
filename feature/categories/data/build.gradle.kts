plugins {
    `java-library`
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(projects.core.network.common)
    implementation(projects.core.network.category)

    implementation(projects.feature.categories.domain)

    implementation(libs.kotlinx.datetime)

    implementation(libs.arrow.core)
    implementation(libs.ktor.client.core)

    implementation(libs.dagger)
}