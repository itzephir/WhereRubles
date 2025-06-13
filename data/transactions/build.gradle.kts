plugins{
    `java-library`
    alias(libs.plugins.kotlin.jvm)
}

dependencies{
    implementation(projects.domain)

    implementation(libs.kotlinx.datetime)
}