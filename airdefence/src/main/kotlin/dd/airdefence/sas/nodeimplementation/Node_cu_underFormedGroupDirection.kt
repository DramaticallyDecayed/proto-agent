package dd.airdefence.sas.nodeimplementation

import dd.airdefence.sas.computation.node.Node_cu_underFormedGroupDirection
import dd.airdefence.sas.objectproperty.UnderFormedGroupDirection
import dd.airdefence.simulation.GlobalArtist
import dd.sas.computation.CalculationResult
import dd.sas.computation.Level
import kotlin.math.abs

class Node_cu_underFormedGroupDirection(level: Level) : Node_cu_underFormedGroupDirection(level) {

    companion object {
        const val ATTACK_ENVELOPE = 30.0
    }

    override fun customProcess(): CalculationResult {
        if (!importantObjectList.isNullOrEmpty() && !importantObjectList.isNullOrEmpty()) {
            val objectsInDanger = formatedGroupList
                .filter {
                    abs(it.vx) < 0.001
                }.map { group ->
                    group to importantObjectList
                        .filter {
                            abs(it.x - group.x) <= ATTACK_ENVELOPE
                        }
                }.forEach{
                    it.second
                        .forEach {importantObject ->
                            val underFormedGroupDirection = UnderFormedGroupDirection()
                            underFormedGroupDirection.domain = importantObject
                            underFormedGroupDirection.range = it.first
                            underFormedGroupDirectionList.add(underFormedGroupDirection)
                        }
                }
            if (!underFormedGroupDirectionList.isNullOrEmpty()) {
                visualize()
                return CalculationResult.POSITIVE
            }
        }
        return CalculationResult.UNKNOWN
    }

    private fun visualize() = GlobalArtist.drawHierarchy(NAME, level.number, subscribers.map { it.name() })

}