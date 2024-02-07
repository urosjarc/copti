plugins {
    kotlin("jvm") version "1.9.21"
    kotlin("plugin.serialization") version "1.9.21"
    id("io.ktor.plugin") version "2.3.8"
}

group = "com.urosjarc"
version = "1.0-SNAPSHOT"

repositories {
    maven {
        setUrl("http://mvn.topobyte.de")
        isAllowInsecureProtocol = true
    }
    maven {
        setUrl("http://mvn.slimjars.com")
        isAllowInsecureProtocol = true
    }
    mavenCentral()
}

dependencies {
    /**
     * Optaplanner
     */
    implementation(platform("org.optaplanner:optaplanner-bom:9.44.0.Final"))
    implementation("org.optaplanner:optaplanner-core")
    testImplementation("org.optaplanner:optaplanner-test")

    /**
     * Small server
     */
    val ktor_version = "2.3.8"
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("io.ktor:ktor-server-cors:$ktor_version")

    /**
     * Mapping
     */
    implementation("de.topobyte:osm4j-xml:0.1.2")
    implementation("de.topobyte:osm4j-pbf:0.1.1")
    implementation("de.topobyte:osm4j-tbo:0.1.0")
    implementation("de.topobyte:osm4j-geometry:0.1.0")

    /**
     * Logging
     */
    runtimeOnly("ch.qos.logback:logback-classic:1.4.14")

    /**
     * Tests
     */
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(19)
}
