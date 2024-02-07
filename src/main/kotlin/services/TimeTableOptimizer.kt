package services

import domain.Lession
import domain.Room
import domain.TimeTable
import usecases.CalculateTimeTableScore
import domain.Timeslot
import org.optaplanner.core.api.solver.SolverFactory
import org.optaplanner.core.config.solver.SolverConfig
import org.slf4j.LoggerFactory
import java.time.DayOfWeek
import java.time.Duration
import java.time.LocalTime

object TimeTableOptimizer {
    private val log = LoggerFactory.getLogger(TimeTableOptimizer::class.java)

    @JvmStatic
    fun main(args: Array<String>) {
        val solverFactory = SolverFactory.create<TimeTable>(
            SolverConfig()
                .withSolutionClass(TimeTable::class.java)
                .withEntityClasses(Lession::class.java)
                .withEasyScoreCalculatorClass(CalculateTimeTableScore::class.java) // The solver runs only for 5 seconds on this small dataset.
                // It's recommended to run for at least 5 minutes ("5m") otherwise.
                .withTerminationSpentLimit(Duration.ofSeconds(5))
        )

        // Load the problem
        val problem = generateDemoData()

        // Solve the problem
        val solver = solverFactory.buildSolver()
        val solution = solver.solve(problem)

        // Visualize the solution
        printTimetable(solution)
    }

    fun generateDemoData(): TimeTable {
        val timeslotList: MutableList<Timeslot> = ArrayList(10)
        timeslotList.add(Timeslot(DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 30)))
        timeslotList.add(Timeslot(DayOfWeek.MONDAY, LocalTime.of(9, 30), LocalTime.of(10, 30)))
        timeslotList.add(Timeslot(DayOfWeek.MONDAY, LocalTime.of(10, 30), LocalTime.of(11, 30)))
        timeslotList.add(Timeslot(DayOfWeek.MONDAY, LocalTime.of(13, 30), LocalTime.of(14, 30)))
        timeslotList.add(Timeslot(DayOfWeek.MONDAY, LocalTime.of(14, 30), LocalTime.of(15, 30)))

        timeslotList.add(Timeslot(DayOfWeek.TUESDAY, LocalTime.of(8, 30), LocalTime.of(9, 30)))
        timeslotList.add(Timeslot(DayOfWeek.TUESDAY, LocalTime.of(9, 30), LocalTime.of(10, 30)))
        timeslotList.add(Timeslot(DayOfWeek.TUESDAY, LocalTime.of(10, 30), LocalTime.of(11, 30)))
        timeslotList.add(Timeslot(DayOfWeek.TUESDAY, LocalTime.of(13, 30), LocalTime.of(14, 30)))
        timeslotList.add(Timeslot(DayOfWeek.TUESDAY, LocalTime.of(14, 30), LocalTime.of(15, 30)))

        val roomList: MutableList<Room> = ArrayList(3)
        roomList.add(Room("Room A"))
        roomList.add(Room("Room B"))
        roomList.add(Room("Room C"))

        val lessonList: MutableList<Lession> = ArrayList()
        var id: Long = 0
        lessonList.add(Lession(id++, "Math", "A. Turing", "9th grade"))
        lessonList.add(Lession(id++, "Math", "A. Turing", "9th grade"))
        lessonList.add(Lession(id++, "Physics", "M. Curie", "9th grade"))
        lessonList.add(Lession(id++, "Chemistry", "M. Curie", "9th grade"))
        lessonList.add(Lession(id++, "Biology", "C. Darwin", "9th grade"))
        lessonList.add(Lession(id++, "History", "I. Jones", "9th grade"))
        lessonList.add(Lession(id++, "English", "I. Jones", "9th grade"))
        lessonList.add(Lession(id++, "English", "I. Jones", "9th grade"))
        lessonList.add(Lession(id++, "Spanish", "P. Cruz", "9th grade"))
        lessonList.add(Lession(id++, "Spanish", "P. Cruz", "9th grade"))

        lessonList.add(Lession(id++, "Math", "A. Turing", "10th grade"))
        lessonList.add(Lession(id++, "Math", "A. Turing", "10th grade"))
        lessonList.add(Lession(id++, "Math", "A. Turing", "10th grade"))
        lessonList.add(Lession(id++, "Physics", "M. Curie", "10th grade"))
        lessonList.add(Lession(id++, "Chemistry", "M. Curie", "10th grade"))
        lessonList.add(Lession(id++, "French", "M. Curie", "10th grade"))
        lessonList.add(Lession(id++, "Geography", "C. Darwin", "10th grade"))
        lessonList.add(Lession(id++, "History", "I. Jones", "10th grade"))
        lessonList.add(Lession(id++, "English", "P. Cruz", "10th grade"))
        lessonList.add(Lession(id++, "Spanish", "P. Cruz", "10th grade"))

        return TimeTable(timeslotList = timeslotList, roomList = roomList, lessonList = lessonList)
    }

    private fun printTimetable(timeTable: TimeTable) {
        timeTable.lessonList
            ?.sortedBy { it.timeslot.hashCode() }
            ?.forEach { println(it) }
    }
}
