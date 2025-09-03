plugins {
    `java-library`
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.18"
    id("xyz.jpenilla.run-paper") version "3.0.0-beta.1"
}

group = "me.alexxxychep.wlhub"
version = "1.0.0-SNAPSHOT"
description = "Test plugin for paperweight-userdev"

java {
    toolchain.languageVersion = JavaLanguageVersion.of(17)
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://hub.spigotmc.org/nexus/content/repositories/public/")
}

dependencies {
    paperweight.paperDevBundle("1.17.1-R0.1-SNAPSHOT")

    // Adventure API - use the same version as Paper
    implementation("net.kyori:adventure-api:4.11.0")
    implementation("net.kyori:adventure-platform-bukkit:4.1.2")

    // Test dependencies
    testImplementation(platform("org.junit:junit-bom:5.11.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation("org.assertj:assertj-core:3.24.2")


    testImplementation("org.mockito:mockito-core:5.8.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.8.0")

    // Use a more recent version of MockBukkit that's compatible with Paper
    testImplementation("com.github.seeseemelk:MockBukkit-v1.17:1.13.0")
}

// Configure Paperweight to exclude NMS from test configurations
paperweight {
    addServerDependencyTo = configurations.named("compileOnly").map { setOf(it) }
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release = 17
    }

    compileTestJava {
        options.encoding = Charsets.UTF_8.name()
    }

    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }

    withType<Test> {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
            showStandardStreams = true
        }
        jvmArgs = listOf("-Xmx512m", "-XX:MaxMetaspaceSize=256m")

        // Exclude Paper-specific tests when using MockBukkit
        exclude("**/*PaperTest*")
    }
}
