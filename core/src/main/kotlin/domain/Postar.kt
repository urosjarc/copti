package domain

import kotlinx.datetime.LocalDate
import utils.Id

data class Postar(
    var id: Id<Postar> = Id(),
    val ime: String,
    val priimek: String,
    val rojstvo: LocalDate,
    val teza: Float,
    val dovolilnice: List<Vozilo.Tip>
)
