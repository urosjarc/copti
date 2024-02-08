package domain

import utils.Id

data class Objekt(
    var id: Id<Objekt> = Id(),
    val naslov: String? = null,
    val tip: Tip,
    val zeljevid: Id<Zemljevid>
) {
    enum class Tip {ZGRADBA, CESTA, PLOCNIK, KOLESARSKI_PAS}
}
