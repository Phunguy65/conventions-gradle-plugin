plugins {
    `version-catalog`
    alias(libs.plugins.vanniktech.publishing)
}

catalog {
    versionCatalog {
        from(files("../gradle/libs.versions.toml"))
    }
}

group = "io.github.phunguy65.build-logic"

mavenPublishing {
    publishToMavenCentral()
    signAllPublications()
    
    coordinates("io.github.phunguy65", project.name, version.toString())
    
    pom {
        name.set("Build Logic Version Catalog")
        description.set("Version catalog for project dependencies")
        url.set("https://github.com/Phunguy65/conventions-gradle-plugin")
        
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        
        developers {
            developer {
                id.set("phunguy65")
                name.set("Nguyễn Ngọc Phú")
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
