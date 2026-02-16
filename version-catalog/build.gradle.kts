plugins {
    `version-catalog`
    id("com.vanniktech.maven.publish") version "0.36.0"
}

catalog {
    versionCatalog {
        from(files("../gradle/libs.versions.toml"))
    }
}

group = "io.github.phunguy65.build-logic"
version = "1.0.0"

mavenPublishing {
    publishToMavenCentral()
    signAllPublications()
    
    coordinates("io.github.phunguy65", "build-logic-catalog", version.toString())
    
    pom {
        name.set("Build Logic Version Catalog")
        description.set("Version catalog for project dependencies")
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
