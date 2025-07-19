plugins {
    `java-library`
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(projects.core.data.common)

    implementation(projects.core.network.account)
    implementation(projects.core.storage.account)

    implementation(libs.arrow.core)

    implementation(libs.ktor.client.core)
    implementation(libs.room.runtime)

    implementation(libs.dagger)
}