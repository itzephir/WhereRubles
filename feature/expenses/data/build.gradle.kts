plugins {
    `java-library`
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ksp)
}

dependencies {
    api(projects.core.model)
    implementation(projects.core.data)
    implementation(projects.core.data.common)
    implementation(projects.core.data.account)
    implementation(projects.core.data.category)
    implementation(projects.core.data.transaction)

    implementation(projects.feature.expenses.domain)

    implementation(libs.kotlinx.datetime)

    implementation(libs.arrow.core)
    implementation(libs.ktor.client.core)

    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
}