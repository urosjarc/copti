plugins {
    this.id("buildSrc.common")
}

dependencies {
    this.implementation(this.project(":core"))
    this.implementation(this.project(":app"))
    /**
     * Small server
     */
    val ktor_version = "2.3.8"
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("io.ktor:ktor-server-cors:$ktor_version")
}
