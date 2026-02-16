import org.gradle.accessors.dm.LibrariesForLibs
import org.springframework.boot.gradle.tasks.run.BootRun

plugins { 
    id("io.phunguy65.jvm-base")
    id("io.spring.dependency-management")
    id("org.springframework.boot")
    id("org.springframework.boot.aot")
    id("org.graalvm.buildtools.native")
}

val libs = the<LibrariesForLibs>()

configurations {
    "developmentOnly" {
        "runtimeClasspath" {
            extendsFrom(configurations["developmentOnly"])
        }
    }
}
dependencies {
    "implementation"(libs.spring.boot.starter.web)
    "implementation"(libs.spring.boot.starter.data.jpa)
    "implementation"(libs.spring.boot.starter.security)
    "implementation"(libs.spring.boot.starter.validation)
    "implementation"(libs.spring.boot.starter.actuator)
    "implementation"(libs.spring.boot.starter.logging)
    "implementation"(libs.spring.boot.starter.aop)
    "developmentOnly"(libs.spring.boot.devtools)
    "runtimeOnly"(libs.postgresql)
    "testImplementation"(libs.spring.boot.starter.test) {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    "annotationProcessor"(libs.spring.boot.starter.configuration.processor)
}  
tasks.named<BootRun>("bootRun") {
    if (project.hasProperty("aot")) {
        jvmArgs("-Dspring.aot.enabled=true")
        systemProperty("spring.aot.enabled", "true")
    }
}