package domain

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty
import org.optaplanner.core.api.domain.solution.PlanningScore
import org.optaplanner.core.api.domain.solution.PlanningSolution
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore

@PlanningSolution // <-- To je tisto kar hoèemo rešiti in za kaj hoèemo dobiti rešitev. notri se nahajajo vsi inputi in outputi.
class TimeTable(

    /**
     * INPUTS
     */
    @ValueRangeProvider // <-- To je provider za možne izbiri na podlagi katere se bo lahko izbirala vrednost v outputu (timeslot v lessionsih)
    @ProblemFactCollectionProperty // <-- Ta kolicina je fiksna in je "problem fact"
    val timeslotList: List<Timeslot>? = null,

    @ValueRangeProvider // <-- To je tudi provider in ponuja izbire iz katerih se lahko izbira vrednost rooma.
    @ProblemFactCollectionProperty // <-- Velja enako je fiksna in je "problem fact"
    val roomList: List<Room>? = null,

    /**
     * Outputs
     */
    @PlanningEntityCollectionProperty // <-- Kateri objekt potrebuje elemente iz value range provider brez katerih ne mora biti inicilizirana.
    val lessonList: List<Lession>? = null
) {
    @PlanningScore // <-- Ocenitev planiranega rezultata
    val score: HardSoftScore? = null
}
