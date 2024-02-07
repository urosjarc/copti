package domain

import java.time.DayOfWeek
import java.time.LocalTime

/**
 * Problem fact (nekaj kar se ne bo spreminjalo)
 * Ce se pouk zacne ob taki pa taki uri in ce je ura lahko dolga 45 min
 * potem je to fiksno!
 */
class Timeslot(
    val dayOfWeek: DayOfWeek,
    val startTime: LocalTime,
    val endTime: LocalTime
) {

    override fun hashCode(): Int {
        return this.dayOfWeek.ordinal * 10e5.toInt() + this.startTime.toSecondOfDay()
    }

    override fun toString(): String = "${this.dayOfWeek} ${this.startTime} -> ${this.endTime} ${this.hashCode()}"
}
