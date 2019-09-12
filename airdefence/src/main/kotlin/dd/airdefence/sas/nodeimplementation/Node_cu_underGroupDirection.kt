package dd.airdefence.sas.nodeimplementation


import dd.airdefence.sas.computation.node.Node_cu_underGroupDirection
import dd.sas.computation.CalculationResult
import dd.sas.computation.Level
import org.slf4j.LoggerFactory

class Node_cu_underGroupDirection(level: Level) : Node_cu_underGroupDirection(level) {

    private val LOG = LoggerFactory.getLogger(Node_cu_underGroupDirection::class.java)

    override fun customProcess(): CalculationResult {
        aircraftGroupList
        importantObjectList
        return CalculationResult.UNKNOWN
    }
}