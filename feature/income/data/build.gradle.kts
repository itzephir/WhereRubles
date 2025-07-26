plugins {
    `java-library`
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.mappie)
}

dependencies {
    implementation(projects.core.model)

    implementation(projects.core.data.common)
    implementation(projects.core.data.account)
    implementation(projects.core.data.category)
    implementation(projects.core.data.transaction)

    implementation(projects.feature.income.domain)

    implementation(libs.kotlinx.datetime)

    implementation(libs.dagger)

    implementation(libs.arrow.core)
    implementation(libs.ktor.client.core)
}