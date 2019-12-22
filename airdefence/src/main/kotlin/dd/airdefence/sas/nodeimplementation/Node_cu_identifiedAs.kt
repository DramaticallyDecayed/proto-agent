package dd.airdefence.sas.nodeimplementation

import dd.airdefence.sas.computation.node.Node_cu_identifiedAs
import dd.airdefence.sas.objectproperty.IdentifiedAs
import dd.airdefence.sas.worldentity.*
import dd.airdefence.simulation.GlobalArtist
import dd.sas.computation.CalculationResult
import dd.sas.computation.Level
import org.slf4j.LoggerFactory

class Node_cu_identifiedAs(level: Level) : Node_cu_identifiedAs(level) {

    private val LOG = LoggerFactory.getLogger(Node_cu_identifiedAs::class.java)

    override fun customProcess(): CalculationResult {
        return if (unknownAircraftList != null && unknownAircraftList.isNotEmpty()) {

            unknownAircraftList
                .forEach {
                    when (it.rcs) {
                        in 1..9 -> {
                            LOG.debug("Detect strike aircraft!")
                            val identifiedAsStrikeAircraft = IdentifiedAs()
                            identifiedAsStrikeAircraft.domain = it

                            val identified = buildAircraft(it, StrikeAircraftC())
                            identifiedAsStrikeAircraft.range = identified

                            identifiedAsList.add(identifiedAsStrikeAircraft)
                            identifiedAircraftList.add(identified)


                        }
                        in 10..50 -> {
                            LOG.debug("Detect reconnaissance aircraft!")
                            val identifiedReconnaissanceAircraft = IdentifiedAs()
                            identifiedReconnaissanceAircraft.domain = it

                            val identified = buildAircraft(it, ReconnaissanceAircraftC())
                            identifiedReconnaissanceAircraft.range = identified

                            identifiedAsList.add(identifiedReconnaissanceAircraft)
                            identifiedAircraftList.add(identified)
                        }
                        !in 1..50 -> {
                            LOG.debug("Detect transport aircraft!")

                            val identifiedTransportAircraft = IdentifiedAs()
                            identifiedTransportAircraft.domain = it

                            val identified = buildAircraft(it, TransportAircraftC())
                            identifiedTransportAircraft.range = identified

                            identifiedAsList.add(identifiedTransportAircraft)
                            identifiedAircraftList.add(identified)
                        }
                    }

                }
            this.identifiedAsList
            visualize()
            CalculationResult.POSITIVE
        } else
            CalculationResult.UNKNOWN
    }

    private fun visualize() = GlobalArtist.drawHierarchy(NAME, level.number, subscribers.map { it.name() })

    private fun buildAircraft(src:UnknownAircraft, dst: IdentifiedAircraft) =
        dst.also {
            it.id = src.id
            it.rcs = src.rcs
            it.x = src.x
            it.y = src.y
            it.vx = src.vx
            it.vy = src.vy
        }

}