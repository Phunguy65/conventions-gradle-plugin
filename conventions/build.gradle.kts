plugins {
    `kotlin-dsl`
    `maven-publish`
    id("pmd")
}

group = "io.phunguy65.build-logic.plugins"
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

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/${System.getenv("GITHUB_REPOSITORY") ?: "OWNER/REPO"}")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
fun DependencyHandlerScope.plugin(plugin: Provider<PluginDependency>) = 
    plugin.map { "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}" }