plugins {
    `kotlin-dsl`
    alias(libs.plugins.changelog)
    alias(libs.plugins.detekt)
    alias(libs.plugins.ktlint)
    id("pmd")
}

group = "io.phunguy65.build-logic"
version = "1.0.1"

pmd{
    toolVersion = libs.versions.pmd.get()
}

dependencies {
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

changelog{
    repositoryUrl = "https://github.com/phunguy65/convention-gradle-plugin"
}