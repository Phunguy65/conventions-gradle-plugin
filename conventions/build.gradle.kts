plugins {
    `kotlin-dsl`
    id("pmd")
    alias(libs.plugins.vanniktech.publishing)
}

group = "io.github.phunguy65.build-logic.plugins"
version = "1.0.0"

pmd {
    toolVersion = libs.versions.pmd.get()
}

dependencies {
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.spring.boot.gradle.plugin)
    implementation(libs.spring.dependency.management.gradle.plugin)
    implementation(libs.hilt.gradle.plugin)
    implementation(libs.pmd)
    implementation(plugin(libs.plugins.jetbrains.compose))
    implementation(plugin(libs.plugins.kotlinx.serialization))
    implementation(plugin(libs.plugins.kotlin.multiplatform))
    implementation(plugin(libs.plugins.android.kotlin.multiplatform.library))
    implementation(plugin(libs.plugins.compose.compiler))
    implementation(plugin(libs.plugins.ksp))
    implementation(plugin(libs.plugins.android.application))
    implementation(plugin(libs.plugins.android.library))
    implementation(plugin(libs.plugins.spring.aot))
    implementation(plugin(libs.plugins.graalvm.native))
    implementation(plugin(libs.plugins.ktlint))
    implementation(plugin(libs.plugins.detekt))
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
    }
}

mavenPublishing {
    publishToMavenCentral()
    signAllPublications()
    
    coordinates("io.github.phunguy65", "build-logic-conventions", version.toString())
    
    pom {
        name.set("Build Logic Conventions")
        description.set("Gradle convention plugins for project configuration")
        url.set("https://github.com/Phunguy65/conventions-gradle-plugin")
        
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        
        developers {
            developer {
                id.set("phunguy65")
                name.set("Phu Nguyen")
                url.set("https://github.com/Phunguy65")
            }
        }
        
        scm {
            url.set("https://github.com/Phunguy65/conventions-gradle-plugin")
            connection.set("scm:git:git://github.com/Phunguy65/conventions-gradle-plugin.git")
            developerConnection.set("scm:git:ssh://git@github.com/Phunguy65/conventions-gradle-plugin.git")
        }
    }
}
fun DependencyHandlerScope.plugin(plugin: Provider<PluginDependency>) = 
    plugin.map { "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}" }