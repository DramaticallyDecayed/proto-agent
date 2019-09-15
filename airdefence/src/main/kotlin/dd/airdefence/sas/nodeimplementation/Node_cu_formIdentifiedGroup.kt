package dd.airdefence.sas.nodeimplementation

import dd.airdefence.sas.computation.node.Node_cu_formIdentifiedGroup
import dd.sas.computation.CalculationResult
import dd.sas.computation.Level
import org.slf4j.LoggerFactory

class Node_cu_formIdentifiedGroup(level: Level): Node_cu_formIdentifiedGroup(level) {
    private val LOG = LoggerFactory.getLogger(Node_cu_formIdentifiedGroup::class.java)

    override fun customProcess(): CalculationResult {
        formGroupList
        return CalculationResult.UNKNOWN
    }
}