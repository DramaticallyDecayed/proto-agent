package dd.airdefence.sas.nodeimplementation

import dd.airdefence.sas.computation.node.Node_cu_threatenedBy
import dd.airdefence.sas.objectproperty.ProduceDanger
import dd.airdefence.sas.objectproperty.ProducedByFormatedGroup
import dd.airdefence.sas.objectproperty.ThreatenedBy
import dd.airdefence.sas.worldentity.DangerC
import dd.airdefence.sas.worldentity.StrikeGroup
import dd.airdefence.simulation.GlobalArtist
import dd.sas.computation.CalculationResult
import dd.sas.computation.Level

class Node_cu_threatenedBy(level: Level) : Node_cu_threatenedBy(level) {
    override fun customProcess(): CalculationResult {
        if (!underFormedGroupDirectionList.isNullOrEmpty()) {
            underFormedGroupDirectionList
                .filter {
                    it.range is StrikeGroup
                }
                .forEach {

                    val danger = DangerC()
                    val producedDanger = ProduceDanger()
                    producedDanger.domain = it.range
                    producedDanger.range = danger
                    produceDangerList.add(producedDanger)

                    val producedByFormatedGroup = ProducedByFormatedGroup()
                    producedByFormatedGroup.domain = danger
                    producedByFormatedGroup.range = it.range
                    producedByFormatedGroupList.add(producedByFormatedGroup)

                    val threatenedBy = ThreatenedBy()
                    threatenedBy.domain = it.domain
                    threatenedBy.range = danger
                    threatenedByList.add(threatenedBy)
                }
            if (!threatenedByList.isNullOrEmpty()) {
                visualize()
                return CalculationResult.POSITIVE
            }
        }
        return CalculationResult.UNKNOWN
    }

    private fun visualize() {
        produceDangerList
            .forEach { produceDanger ->
                val threatenedBy = threatenedByList
                    .first { it.range == produceDanger.range }
                GlobalArtist
                    .drawDanger(
                        threatenedBy.domain.x + 50.0 / 2.0,
                        threatenedBy.domain.y + 50.0 / 2.0,
                        50.0,
                        produceDanger.domain.x,
                        produceDanger.domain.y + Node_cu_AircraftGroup.DISTANCE_TO_BE_GROUP
                    )
            }
        GlobalArtist.drawHierarchy(NAME, level.number, subscribers.map { it.name() })
    }
}