package domain

import utils.Id

data class Package(
    val id: Id<Package>,
    val name: String,
    val weight: Double,
    val volume: Double,
    val destination: Id<MapFeature>,
)
