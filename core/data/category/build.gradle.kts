plugins {
    `java-library`
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.mappie)
}

dependencies {
    implementation(projects.core.data.common)

    implementation(projects.core.network)
    implementation(projects.core.network.category)
    implementation(projects.core.storage)
    implementation(projects.core.storage.category)

    implementation(libs.arrow.core)

    implementation(libs.ktor.client.core)

    implementation(libs.dagger)
}