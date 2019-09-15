package dd.airdefence.simulation

import dd.airdefence.perception.APerception
import dd.airdefence.sas.worldentity.ImportantObject
import dd.airdefence.sas.worldentity.UnknownAircraft
import dd.airdefence.simulation.model.CoveredObject
import dd.airdefence.simulation.model.FightJet
import dd.airdefence.simulation.model.Radar
import dd.airdefence.simulation.model.SceneObject
import kotlin.math.pow

class SimulationPerception(
    val scene: List<SceneObject>,
    val radar: Radar
) : APerception(mutableListOf(), mutableListOf()) {


    override fun cycle() {
        importantObjects.clear()
        unknownAircrafts.clear()
        scene
            .map { Pair(it.type(), it.radarSignature()) }
            .filter { it.second != null }
            .forEach {
                when (it.first) {
                    FightJet::class.java.simpleName -> if (insideRadarZone(it.second as UnknownAircraft)) unknownAircrafts.add(it.second as UnknownAircraft)
                    CoveredObject::class.java.simpleName -> importantObjects.add(it.second as ImportantObject)
                    else -> throw  IllegalArgumentException("Unknown type ${it.first}")
                }
            }
    }

    private fun insideRadarZone(unknownAircraft: UnknownAircraft) =
        ((radar.x - unknownAircraft.x).pow(2) + (radar.y - unknownAircraft.y).pow(2)).pow(0.5) <= radar.radarArea

}