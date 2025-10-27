plugins {
    base
    jacoco

    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.spring)

    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependencyManagement)

    alias(libs.plugins.ktlint)
}

group = "gho.workshop"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.spring.ai.starter.mcp.server)
}

tasks.test {
    useJUnitPlatform()
}
