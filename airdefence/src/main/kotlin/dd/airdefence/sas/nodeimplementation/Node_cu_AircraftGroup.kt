package dd.airdefence.sas.nodeimplementation

import dd.airdefence.sas.computation.node.Node_cu_AircraftGroup
import dd.sas.computation.CalculationResult
import dd.sas.computation.Level
import org.slf4j.LoggerFactory

class Node_cu_AircraftGroup(level: Level) : Node_cu_AircraftGroup(level) {

    private  val LOG = LoggerFactory.getLogger(Node_cu_AircraftGroup::class.java)

    override fun customProcess(): CalculationResult {
        unknownAircraftList
        return CalculationResult.UNKNOWN
    }
}