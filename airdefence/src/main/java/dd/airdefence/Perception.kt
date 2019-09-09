package dd.airdefence

import dd.airdefence.sas.worldentity.ImportantObject
import dd.airdefence.sas.worldentity.ImportantObjectC
import dd.airdefence.sas.worldentity.UnknownAircraft
import dd.airdefence.sas.worldentity.UnknownAircraftC
import org.slf4j.LoggerFactory
import java.io.File
import java.util.*

class Perception {

    val LOG = LoggerFactory.getLogger(Perception::class.java)

    val importantObjects = mutableListOf<ImportantObject>()
    val unknownAircrafts = mutableListOf<UnknownAircraft>()

    var cycleCounter = 0

    val scenario = mutableMapOf<Int, MutableList<PerceptedObject>>()


    constructor() {
        val scenarioFilePath = Perception::class.java.classLoader.getResource("scenario.csv").path
        LOG.debug("Reading scenario from $scenarioFilePath...")
        val scanner = Scanner(File(scenarioFilePath))
        //skip header
        scanner.nextLine()
        var previousCycle = 0
        while (scanner.hasNext()) {
            val scenarioLine = scanner.nextLine().split(",")
            val cycle = scenarioLine[0].toInt()
            require(cycle >= previousCycle) {
                "Next cycle can't be less than previous one: $cycle < $previousCycle"
            }
            scenario
                .computeIfAbsent(cycle) { mutableListOf() }
                .add(
                    PerceptedObject(
                        scenarioLine[1],
                        scenarioLine[2].toInt(),
                        scenarioLine[3].toInt(),
                        scenarioLine[4].toInt(),
                        scenarioLine[5].toInt(),
                        scenarioLine[6].toInt(),
                        scenarioLine[7].toInt()
                    )
                )
            previousCycle = cycle
        }
        LOG.debug("Have read ${scenario.size} scenario object actions")
    }

    fun cycle() {
        importantObjects.clear()
        unknownAircrafts.clear()

        cycleCounter++

        scenario[cycleCounter]
            ?.forEach {
                when (it.type) {
                    "ImportantObject" -> importantObjects.add(buildimportantObject(it))
                    "UnknownAircraft" -> unknownAircrafts.add(buildUnknownAircraft(it))
                    else -> throw IllegalArgumentException("Unknown type ${it.type}")
                }
            }
        LOG.debug("Percept ${importantObjects} important objects")
        LOG.debug("Percept ${unknownAircrafts} unknown aircrafts")
    }

    private fun buildimportantObject(perceptedObject: PerceptedObject) =
        ImportantObjectC().also {
            it.id = perceptedObject.id
            it.x = perceptedObject.x
            it.y = perceptedObject.y
        }

    private fun buildUnknownAircraft(perceptedObject: PerceptedObject) =
        UnknownAircraftC().also {
            it.id = perceptedObject.id
            it.rcs = perceptedObject.rcs
            it.x = perceptedObject.x
            it.y = perceptedObject.y
            it.vx = perceptedObject.vx
            it.vy = perceptedObject.vy
        }

    data class PerceptedObject(
        val type: String,
        val id: Int,
        val rcs: Int,
        val x: Int,
        val y: Int,
        val vx: Int,
        val vy: Int
    )

}