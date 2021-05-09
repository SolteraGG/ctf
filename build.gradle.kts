import kr.entree.spigradle.kotlin.paper
import kr.entree.spigradle.kotlin.papermc
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.4.10"
    id("com.diffplug.spotless") version "5.8.2"
    id("com.github.johnrengelman.shadow") version "5.2.0"
    id("kr.entree.spigradle") version "2.2.3"
}

group = "com.dumbdogdiner.ctf"
version = "1.0.0"

repositories {
    jcenter()
    mavenCentral()
    // Add paper repository here, as it's used in both API and Bukkit modules.
    papermc()
    // command api repositories
    maven { url = uri("https://raw.githubusercontent.com/JorelAli/CommandAPI/mvn-repo/") }
    maven { url = uri("https://repo.codemc.org/repository/maven-public/") }
}

dependencies {
    implementation(kotlin("stdlib"))
    // paper
    compileOnly(paper())
    // command api
    implementation("dev.jorel:commandapi-shade:5.10")
    compileOnly("dev.jorel:commandapi-core:5.10")
    compileOnly("dev.jorel:commandapi-annotations:5.3")
    annotationProcessor("dev.jorel:commandapi-annotations:5.3")
}

spotless {
    ratchetFrom = "origin/master"
    kotlin {
        ktlint()
        licenseHeaderFile(rootProject.file("LICENSE_HEADER"))
    }
}

tasks {
    build {
        dependsOn("shadowJar")
    }

    shadowJar {
        archiveBaseName.set("CaptureTheFox")
        archiveClassifier.set("")
    }

    compileJava {
        targetCompatibility = JavaVersion.VERSION_14.toString()
        sourceCompatibility = JavaVersion.VERSION_14.toString()
    }

    compileKotlin {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_14.toString()
    }

    spigot {
        name = "CaptureTheFox"
    }
}
