package domain

import org.optaplanner.core.api.domain.entity.PlanningEntity
import org.optaplanner.core.api.domain.lookup.PlanningId
import org.optaplanner.core.api.domain.variable.PlanningVariable

/**
 * Lession pa je nekaj kar se lahko spreminja
 * Lession ima neke podatke tudi fiksne, imas ucitelja ki bo za neko leto predaval
 * nek specificen fiksen subject neki specificni grupi studentov (facts)
 * Vendar predavanje se lahko izvaja ob neki uri v neki doloceni predavalnici
 * in to se spreminja in je stvar debate zato hocemo to optimatizirati.
 */
@PlanningEntity
class Lession(
    @PlanningId
    val id: Long? = null,
    val subject: String? = null,
    val teacher: String? = null,
    val studentGroup: String? = null,
) {

    @PlanningVariable
    val timeslot: Timeslot? = null

    @PlanningVariable
    val room: Room? = null

    override fun toString(): String {
        return listOf(
            "Time and place: $timeslot $room",
            "Subject: $subject",
            "Teacher: $teacher",
            "Grup: $studentGroup"
        ).joinToString("\n") + "\n"
    }

}
