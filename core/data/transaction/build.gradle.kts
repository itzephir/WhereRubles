plugins {
    `java-library`
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ksp)
    alias(libs.plugins.mappie)
}

dependencies {
    implementation(projects.core.data.common)
    implementation(projects.core.network)
    implementation(projects.core.network.transaction)
    implementation(projects.core.storage)
    implementation(projects.core.storage.transaction)

    implementation(libs.arrow.core)

    implementation(libs.ktor.client.core)
    implementation(libs.androidx.room.runtime)

    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
}