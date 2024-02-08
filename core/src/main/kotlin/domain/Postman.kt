package domain

import utils.Id

data class Postman(
    val id: Id<Postman>,
    val name: String,
    val surname: String,
    val age: String,
    val weight: Float,
    val licenses: List<Vehicle.Type>
)
