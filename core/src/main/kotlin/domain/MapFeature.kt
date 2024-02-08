package domain

import utils.Id

data class MapFeature(
    val id: Id<MapFeature>,
    val address: String? = null,
    val type: Type
) {
    enum class Type {BUILDING, MOTORWAY, SIDEWALK, CYCLEWAY}
}
