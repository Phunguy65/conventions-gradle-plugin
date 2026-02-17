plugins {
    `kotlin-dsl`
    alias(libs.plugins.changelog)
    alias(libs.plugins.detekt)
    alias(libs.plugins.ktlint)
    id("pmd")
    alias(libs.plugins.axion.release)
}

scmVersion {
    tag {
        prefix.set("build-logic")
        versionSeparator.set("-")
    }
}

group = "io.phunguy65.build-logic"
version = scmVersion.version

subprojects {
    version = rootProject.version
}

pmd {
    toolVersion = libs.versions.pmd.get()
}

dependencies {
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

changelog {
    repositoryUrl = "https://github.com/phunguy65/convention-gradle-plugin"
}
