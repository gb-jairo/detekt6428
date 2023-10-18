plugins {
    kotlin("jvm") version "1.9.10"
    id("io.gitlab.arturbosch.detekt") version("1.23.1")

    application
}

allprojects {
    version = "1.0"
    group = "com.example"
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "io.gitlab.arturbosch.detekt")

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    }
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    testImplementation(kotlin("test"))

    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.1")
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-rules-libraries:1.23.1")
    detekt(project(":detekt-custom-rules"))
}

detekt {
    autoCorrect = true
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}