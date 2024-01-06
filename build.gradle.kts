import org.jetbrains.gradle.ext.settings
import org.jetbrains.gradle.ext.taskTriggers

plugins {
    idea
    alias(catalog.plugins.idea.ext)

    alias(catalog.plugins.spotless)

    alias(catalog.plugins.semver)
}

group = "settingdust.heraclesforblabber"

version = semver.semVersion.toString()

allprojects { repositories { mavenCentral() } }

subprojects {
    group = rootProject.group
    version = rootProject.version

    repositories {
        maven("https://maven.terraformersmc.com/releases") {
            content { includeGroup("com.terraformersmc") }
        }
        maven("https://maven.resourcefulbees.com/repository/maven-public/") {
            content { includeGroupAndSubgroups("com.teamresourceful") }
        }
        maven("https://maven.resourcefulbees.com/repository/terrarium/") {
            content { includeGroupAndSubgroups("earth.terrarium") }
        }
        maven("https://maven.ladysnake.org/releases") {
            name = "Ladysnake Mods"
            content {
                includeGroupByRegex("(org|io\\.github).ladysnake.*")
                includeGroupByRegex("(dev|io\\.github)\\.onyxstudios.*")
            }
        }
        maven("https://api.modrinth.com/maven") {
            name = "Modrinth"
            content { includeGroup("maven.modrinth") }
        }
        maven("https://oss.sonatype.org/content/repositories/snapshots") {
            name = "Nexus Repository Manager"
        }
    }
}

spotless {
    java {
        target("*/src/**/*.java")
        palantirJavaFormat("2.29.0")
    }

    kotlin {
        target("*/src/**/*.kt", "*/*.gradle.kts", "*.gradle.kts")
        ktfmt("0.46").kotlinlangStyle()
    }
}

idea.project.settings.taskTriggers { afterSync(":forge:genIntellijRuns") }
