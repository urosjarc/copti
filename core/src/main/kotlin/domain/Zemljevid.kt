package domain

import utils.Id

data class Zemljevid(
    var id: Id<Zemljevid> = Id(),
    val ime: String,
    val opis: String,
    val longMin: Float,
    val longMax: Float,
    val latiMin: Float,
    val latiMax: Float,
)
