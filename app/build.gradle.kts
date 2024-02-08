plugins {
    this.id("buildSrc.common")
    this.id("buildSrc.logging")
    this.id("buildSrc.serialization")
    this.id("buildSrc.datetime")
    this.id("buildSrc.injections")
    this.id("buildSrc.db")
}

dependencies {
    this.implementation(this.project(":core"))
    /**
     * Optaplanner
     */
    implementation(platform("org.optaplanner:optaplanner-bom:9.44.0.Final"))
    implementation("org.optaplanner:optaplanner-core")
    testImplementation("org.optaplanner:optaplanner-test")

    /**
     * OSM file parsing
     */
    implementation("de.topobyte:osm4j-xml:1.3.0")
    implementation("de.topobyte:osm4j-pbf:1.3.0")
    implementation("de.topobyte:osm4j-tbo:1.3.0")
    implementation("de.topobyte:osm4j-geometry:1.3.0")
}
