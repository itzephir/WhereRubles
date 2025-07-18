plugins {
    `java-library`
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(projects.core.network.common)
    implementation(projects.core.network.transaction)
    implementation(projects.core.network.account)

    implementation(projects.feature.income.domain)

    implementation(libs.kotlinx.datetime)

    implementation(libs.dagger)

    implementation(libs.arrow.core)
    implementation(libs.ktor.client.core)
}