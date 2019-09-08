package dd.airdefence.sas.nodeimplementation

import dd.airdefence.sas.computation.node.Node_cu_UnknownAircraft
import dd.sas.computation.CalculationResult
import dd.sas.computation.Level
import org.slf4j.LoggerFactory

class Node_cu_UnknownAircraft (level: Level) : Node_cu_UnknownAircraft(level) {

    private val LOG = LoggerFactory.getLogger(Node_cu_UnknownAircraft::class.java)

    override fun customProcess(): CalculationResult {
        unknownAircraftList.forEach { LOG.debug("$NAME receive from perception system UnknownAircraft ${it.id}")}
        return if(unknownAircraftList.isEmpty())
            CalculationResult.UNKNOWN
        else
            CalculationResult.POSITIVE
    }
}