package dd.airdefence.sas.nodeimplementation

import dd.airdefence.sas.computation.node.Node_cu_ImportantObject
import dd.airdefence.simulation.GlobalArtist
import dd.sas.computation.CalculationResult
import dd.sas.computation.Level
import org.slf4j.LoggerFactory

class Node_cu_ImportantObject(level: Level) : Node_cu_ImportantObject(level) {

    private val LOG = LoggerFactory.getLogger(Node_cu_ImportantObject::class.java)

    override fun customProcess(): CalculationResult {
        importantObjectList.forEach { LOG.debug("$NAME receive ${it.id}") }
        return if (importantObjectList.isEmpty())
            CalculationResult.UNKNOWN
        else {
            visualize()
            CalculationResult.POSITIVE
        }
    }

    private fun visualize() = GlobalArtist.drawHierarchy(NAME, level.number, subscribers.map { it.name() })


}