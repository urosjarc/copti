package domain

import utils.Id

data class Vektor(
    var id: Id<Vektor> = Id(),
    val objekt: Id<Objekt>,
    val long: Float,
    val lati: Float,
    val visina:Float
)
