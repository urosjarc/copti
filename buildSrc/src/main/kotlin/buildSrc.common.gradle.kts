plugins {
    kotlin("jvm")
}

group = "si.urosjarc"
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
    maven {
        setUrl("https://jitpack.io")
    }
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test-junit5"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(19)
}
