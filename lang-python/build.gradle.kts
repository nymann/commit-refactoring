plugins {
    id("java")
    id("org.jetbrains.intellij.platform.module")
}

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

dependencies {
    implementation(project(":core"))

    intellijPlatform {
        intellijIdea(providers.gradleProperty("platformVersion"))
        plugins(listOf("PythonCore:263.7307.353"))
    }
}
