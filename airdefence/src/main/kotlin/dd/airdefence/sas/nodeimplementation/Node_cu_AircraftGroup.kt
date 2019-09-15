package dd.airdefence.sas.nodeimplementation

import dd.airdefence.sas.computation.node.Node_cu_AircraftGroup
import dd.airdefence.sas.objectproperty.FormGroup
import dd.airdefence.sas.worldentity.AircraftGroupC
import dd.airdefence.simulation.GlobalArtist
import dd.sas.computation.CalculationResult
import dd.sas.computation.Level
import org.slf4j.LoggerFactory
import kotlin.math.pow

class Node_cu_AircraftGroup(level: Level) : Node_cu_AircraftGroup(level) {

    companion object {
        const val DISTANCE_TO_BE_GROUP = 80.0
    }

    private val LOG = LoggerFactory.getLogger(Node_cu_AircraftGroup::class.java)

    override fun customProcess(): CalculationResult {
        LOG.debug("Identified ${identifiedAircraftList.size}!")

        val groupping = mutableMapOf<Int, Int>()
        identifiedAircraftList
            .map { identifiedAircraft ->
                identifiedAircraftList
                    .filterNot { other ->
                        other.id == identifiedAircraft.id
                    }
                    .map { other ->
                        Triple(
                            identifiedAircraft.id,
                            other.id,
                            distance(
                                identifiedAircraft.x,
                                identifiedAircraft.y,
                                other.x,
                                other.y
                            )
                        )
                    }
            }
            .flatten()
            .filter { it.third < DISTANCE_TO_BE_GROUP }
            .map { distanceBetween ->
                groupping.putIfAbsent(distanceBetween.first, distanceBetween.first)
                groupping.putIfAbsent(distanceBetween.second, distanceBetween.first)
            }

        val groups = groupping
            .map { it.value to it.key }
            .groupBy({ it.first }, { it.second })


        return if (groups.isNotEmpty()) {
            groups
                .forEach {
                    val aircraftGroup = AircraftGroupC()
                    aircraftGroup.id = it.key
                    aircraftGroupList.add(aircraftGroup)
                    it
                        .value
                        .forEach { groupped ->
                            val formGroup = FormGroup()
                            formGroup.domain = identifiedAircraftList.find { identifiedAircraf ->
                                identifiedAircraf.id == groupped
                            }
                            formGroup.range = aircraftGroup
                            formGroupList.add(formGroup)
                        }
                }
            visualize()
            CalculationResult.POSITIVE
        } else {
            CalculationResult.UNKNOWN
        }
    }

    private fun visualize(){
        aircraftGroupList
            .map { group ->
                val aircraft = formGroupList.find { it.domain.id == group.id }
                if (aircraft != null)
                    GlobalArtist.drawGroup(
                        aircraft.domain.x,
                        aircraft.domain.y,
                        DISTANCE_TO_BE_GROUP
                    )
            }
    }

    private fun distance(x1: Double, y1: Double, x2: Double, y2: Double) =
        ((x1 - x2).toFloat().pow(2) + (y1 - y2).toFloat().pow(2)).pow(0.5f).toInt()


}