package dd.airdefence.simulation

import dd.airdefence.perception.APerception
import dd.airdefence.sas.worldentity.ImportantObject
import dd.airdefence.sas.worldentity.UnknownAircraft
import dd.airdefence.simulation.model.CoveredObject
import dd.airdefence.simulation.model.FightJet
import dd.airdefence.simulation.model.SceneObject
import java.lang.IllegalArgumentException

class SimulationPerception(
    val scene: List<SceneObject>
) : APerception(mutableListOf(), mutableListOf()) {


    override fun cycle() {
        importantObjects.clear()
        unknownAircrafts.clear()
        scene
            .map { Pair(it.type(), it.radarSignature()) }
            .filter { it.second != null }
            .map {
                when (it.first) {
                    FightJet::class.java.simpleName -> unknownAircrafts.add(it.second as UnknownAircraft)
                    CoveredObject::class.java.simpleName -> importantObjects.add(it.second as ImportantObject)
                    else -> throw  IllegalArgumentException("Unknown type ${it.first}")
                }
            }
    }

}