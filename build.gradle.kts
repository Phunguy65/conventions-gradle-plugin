plugins {
    `kotlin-dsl`
    alias(libs.plugins.changelog)
}

group = "io.phunguy65.build-logic"
version = "1.0.0"

dependencies {
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

changelog{
    repositoryUrl = "https://github.com/phunguy65/convention-gradle-plugin"
}