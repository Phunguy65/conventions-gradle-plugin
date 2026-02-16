import com.android.build.api.dsl.LibraryExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.dependencies
plugins { 
    id("io.github.phunguy65.jvm-base")
    id("com.android.library")
    id("com.google.dagger.hilt.android")
}

val libs = the<LibrariesForLibs>()

configure<LibraryExtension> {
    compileSdk = libs.versions.androidCompileSdk.get().toInt()
     defaultConfig {
        minSdk = libs.versions.androidMinSdk.get().toInt()
    }
}

dependencies {
    "implementation"(libs.hilt.android)
    "annotationProcessor"(libs.hilt.compiler)
    "implementation"(libs.retrofit2)
    "implementation"(libs.retrofit2.gson)
    "implementation"(libs.okhttp3)
    "implementation"(libs.okhttp3.loggingInterceptor)
} 