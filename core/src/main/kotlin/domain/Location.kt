package domain

import utils.Id

data class Location(
    val id: Id<Location>,
    val parent: Id<MapFeature>,
    val long: Float,
    val lati: Float,
    val elevation:Float
)
