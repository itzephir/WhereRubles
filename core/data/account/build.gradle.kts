plugins {
    `java-library`
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(projects.core.data.common)

    implementation(projects.core.network.account)
    implementation(projects.core.storage)

    implementation(libs.arrow.core)

    implementation(libs.ktor.client.core)
    implementation(libs.androidx.room.runtime)

    implementation(libs.dagger)
}