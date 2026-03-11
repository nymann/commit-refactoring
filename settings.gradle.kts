plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.9.0"
}

rootProject.name = "commit-refactoring"

include(":core")
include(":lang-java")
include(":lang-python")
