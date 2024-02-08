plugins {
    this.`kotlin-dsl`
}
repositories {
    this.gradlePluginPortal()
}
dependencies {
    this.implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
    this.implementation("org.jetbrains.kotlin:kotlin-serialization:1.7.10")
}
