package domain

import utils.Id

data class Paket(
    var id: Id<Paket> = Id(),
    val ime: String,
    val teza: Double,
    val volumen: Double,
    val destinacija: Id<Objekt>,
)
