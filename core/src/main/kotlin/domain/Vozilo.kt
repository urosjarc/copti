package domain

import utils.Id

data class Vozilo(
    var id: Id<Vozilo> = Id(),
    val tip: Tip,
    val praznaTeza: Float,
    val cenaNaKm: Float,
    val maxTeza: Float,
    val maxNosilniVolumen: Float,
    val maxHitrost: Float,
) {
    enum class Tip {
        BODY, CAR, BICYCLE, MOTORCYCLE
    }
}
