plugins {
    `java-library`
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.google.ksp)
}

dependencies {
    implementation(projects.core.network.common)
    implementation(projects.core.network.transaction)
    implementation(projects.core.network.account)

    implementation(projects.feature.expenses.domain)

    implementation(libs.kotlinx.datetime)

    implementation(libs.arrow.core)
    implementation(libs.ktor.client.core)

    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
}