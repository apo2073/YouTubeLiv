plugins {
    id("java")
}

group = "kr.apo2073"
version = "1.0"

repositories {
    mavenCentral()
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