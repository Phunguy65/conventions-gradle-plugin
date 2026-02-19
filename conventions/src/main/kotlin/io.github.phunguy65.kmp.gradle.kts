import com.android.build.api.dsl.LibraryExtension
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.compose.ComposeExtension
import org.gradle.accessors.dm.LibrariesForLibs
plugins {
    id ("io.github.phunguy65.jvm-base")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.google.devtools.ksp")
    id("org.jlleitschuh.gradle.ktlint")
    id("dev.detekt")
}

version = "1.0.1"

val libs = the<LibrariesForLibs>()
val javaVersion = JavaVersion.toVersion(libs.versions.java.get())
val kotlinVersion = libs.versions.kotlin.get()

val compose = the<ComposeExtension>().dependencies

configure<KotlinMultiplatformExtension> {
    compilerOptions{
        jvm {
            compilerOptions{
                jvmTarget = JvmTarget.fromTarget(
                    JavaVersion.toVersion(
                        javaVersion
                    ).majorVersion
                )
            }
        }
        apiVersion = KotlinVersion.fromVersion(
            kotlinVersion
        )
        languageVersion = KotlinVersion.fromVersion(
            kotlinVersion
        )
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                implementation(project.dependencies.platform(libs.koin.bom))
                implementation(libs.koin.compose)
                implementation(libs.koin.compose.viewmodel)
                implementation(libs.koin.compose.viewmodel.navigation)
                implementation(libs.koin.annotations)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.client.okhttp)
                implementation(libs.koin.android)
                implementation(libs.androidx.activity.compose)
            }
        }

        val iosMain by getting {
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }
    }
}

configure<LibraryExtension>{
    compileSdk = libs.versions.androidCompileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.androidMinSdk.get().toInt()
    }
}
detekt{
    parallel = true
    allRules = true
}

dependencies {
    add("kspCommonMainMetadata", libs.koin.ksp.compiler)
    add("kspCommonMain", libs.koin.ksp.compiler)
}

