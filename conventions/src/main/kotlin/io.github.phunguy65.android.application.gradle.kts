import com.android.build.api.dsl.ApplicationExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.configure

plugins {
    id("io.github.phunguy65.jvm-base")
    id("com.android.application")
}

val libs = the<LibrariesForLibs>()

configure<ApplicationExtension>{
    compileSdk = libs.versions.androidCompileSdk.get().toInt()
     defaultConfig {
        targetSdk = libs.versions.androidTargetSdk.get().toInt()
    }
}