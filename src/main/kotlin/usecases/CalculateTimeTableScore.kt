package usecases

import domain.Lession
import domain.TimeTable
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore
import org.optaplanner.core.api.score.calculator.EasyScoreCalculator


class CalculateTimeTableScore : EasyScoreCalculator<TimeTable, HardSoftScore> {
    override fun calculateScore(timeTable: TimeTable): HardSoftScore {
        val lessonList: List<Lession> = timeTable.lessonList ?: throw Exception("Lession list not inited")
        var hardScore = 0
        for (a in lessonList) {
            for (b in lessonList) {
                if (a.timeslot != null && a.timeslot == b.timeslot && a.id!! < b.id!!) {
                    // A room can accommodate at most one lesson at the same time.
                    if (a.room != null && a.room == b.room) {
                        hardScore--
                    }
                    // A teacher can teach at most one lesson at the same time.
                    if (a.teacher == b.teacher) {
                        hardScore--
                    }
                    // A student can attend at most one lesson at the same time.
                    if (a.studentGroup == b.studentGroup) {
                        hardScore--
                    }
                }
            }
        }
        val softScore = 0
        // Soft constraints are only implemented in the optaplanner-quickstarts code
        return HardSoftScore.of(hardScore, softScore)
    }
}
