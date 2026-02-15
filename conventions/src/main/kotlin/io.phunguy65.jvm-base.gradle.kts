import org.gradle.kotlin.dsl.`java-base`
import org.gradle.accessors.dm.LibrariesForLibs
plugins {
    `java-base`
	id("pmd")
}

val libs = the<LibrariesForLibs>()
val javaVersion = JavaVersion.toVersion(libs.versions.java.get())

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(javaVersion.majorVersion))
	}    
}
