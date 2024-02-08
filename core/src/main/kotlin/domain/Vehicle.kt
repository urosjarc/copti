package domain

import utils.Id

data class Vehicle(
    val id: Id<Vehicle>,
    val type: Type,
    val emptyWeight: Float,
    val costPerKm: Float,
    val maxCarryWeight: Float,
    val maxCarryVolume: Float,
    val maxSpeed: Float,
) {
    enum class Type {
        BODY, CAR, BICYCLE, MOTORCYCLE
    }
}
