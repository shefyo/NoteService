plugins {
    kotlin("jvm") version "1.9.21"
    id("jacoco")
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("org.jetbrains.kotlin:kotlin-stdlib")
    testImplementation("junit:junit:4.13.2")
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}
