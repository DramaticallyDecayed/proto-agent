package dd.airdefence.sas.nodeimplementation

import dd.airdefence.sas.computation.node.Node_cu_identifiedAs
import dd.sas.computation.CalculationResult
import dd.sas.computation.Level
import org.slf4j.LoggerFactory

class Node_cu_identifiedAs(level: Level) : Node_cu_identifiedAs(level) {

    private  val LOG = LoggerFactory.getLogger(Node_cu_identifiedAs::class.java)

    override fun customProcess(): CalculationResult {
        unknownAircraftList
        return CalculationResult.UNKNOWN
    }

}