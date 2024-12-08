plugins {
    id("java")
    `maven-publish`
    `java-library`
}

group = "com.github.apo2073"
version = "1.1"

repositories {
    mavenCentral()
    maven("https://oss.sonatype.org/content/groups/public/") {
        name = "sonatype"
    }
    maven("https://jitpack.io") {
        name = "jitpack"
    }
    maven { url = uri("https://repo.gradle.org/gradle/repo") }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("com.google.api-client:google-api-client:1.33.0")
    implementation("com.google.oauth-client:google-oauth-client-jetty:1.23.0")
    implementation("com.google.apis:google-api-services-youtube:v3-rev20230816-2.0.0")
    implementation("com.google.http-client:google-http-client-jackson2:1.39.2")
}

tasks.jar {
    archiveFileName.set("YouTubeLiv-${version}.jar")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.github.apo2073"
            artifactId = "YoutubeLiv"
            version = "1.1"

            from(components["java"])

            pom {
                name.set("YouTubeLiv")
                licenses {
                    license {
                        name="GNU GENERAL PUBLIC LICENSE"
                        url="https://www.gnu.org/licenses/gpl-3.0.en.html#license-text"
                    }
                }
                developers {
                    developer {
                        id="apo2073"
                        url="https://github.com/apo2073"
                    }
                }
            }
        }
    }
}