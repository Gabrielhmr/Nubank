plugins {
    kotlin("jvm") version "1.9.23"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.3")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

application {
    mainClass.set("MainKt")
}

tasks.named<Jar>("jar") {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    from({
        configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }
    })
}