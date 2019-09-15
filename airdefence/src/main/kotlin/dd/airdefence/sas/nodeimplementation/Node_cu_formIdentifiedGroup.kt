package dd.airdefence.sas.nodeimplementation

import dd.airdefence.sas.computation.node.Node_cu_formIdentifiedGroup
import dd.airdefence.sas.objectproperty.FormGroup
import dd.airdefence.sas.objectproperty.FormIdentifiedGroup
import dd.airdefence.sas.objectproperty.IdentifiedAsFormation
import dd.airdefence.sas.worldentity.FormatedGroup
import dd.airdefence.sas.worldentity.MarchGroupC
import dd.airdefence.sas.worldentity.StrikeGroupC
import dd.airdefence.simulation.GlobalArtist
import dd.sas.computation.CalculationResult
import dd.sas.computation.Level
import org.slf4j.LoggerFactory

class Node_cu_formIdentifiedGroup(level: Level) : Node_cu_formIdentifiedGroup(level) {
    private val LOG = LoggerFactory.getLogger(Node_cu_formIdentifiedGroup::class.java)

    override fun customProcess(): CalculationResult {

        if (formGroupList != null && formGroupList.isNotEmpty()) {
            formGroupList
                .groupBy({ it.range.id }, { it })
                .map {
                    when (it.value.size) {
                        in 3..Int.MAX_VALUE -> buildGroup(it, MarchGroupC())
                        2 -> smallGroup(it)
                    }
                }
            visualize()
            return CalculationResult.POSITIVE
        } else {
            return CalculationResult.UNKNOWN
        }
    }

    private fun smallGroup(it: Map.Entry<Int, List<FormGroup>>) {
        val ethalon = it.value.first().domain.x
        val inline = it.value
            .map {
                it.domain
            }
            .fold(true) { acc, identifiedAircraft ->
                (identifiedAircraft.x - ethalon) < 1.0 && acc
            }
        if (!inline)
            buildGroup(it, MarchGroupC())
        else
            buildGroup(it, StrikeGroupC())
    }

    private fun buildGroup(
        it: Map.Entry<Int, List<FormGroup>>,
        identifiedGroupFormation: FormatedGroup
    ) {
        val group = it.value.first().range
        val formatedGroup = identifiedGroupFormation
        formatedGroup.id = group.id
        val identifiedAsFormation = IdentifiedAsFormation()
        identifiedAsFormation.domain = group
        identifiedAsFormation.range = formatedGroup
        it.value
            .map { group -> group.domain }
            .forEach { identifiedAircraft ->
                val formIdentifiedGroup = FormIdentifiedGroup()
                formIdentifiedGroup.domain = identifiedAircraft
                formIdentifiedGroup.range = formatedGroup
            }
        formatedGroupList.add(formatedGroup)

        val mainAircraft = it.value.map { it.domain }.find { it.id == group.id }
        if (mainAircraft != null) {
            formatedGroup.x = mainAircraft.x
            formatedGroup.y = mainAircraft.y
            formatedGroup.vx = mainAircraft.vx
            formatedGroup.vy = mainAircraft.vy
        }
    }

    private fun visualize() {
        formatedGroupList
            .map { group ->
                val aircraft = formGroupList.find { it.domain.id == group.id }
                if (aircraft != null) {
                    val name = group::class.java.interfaces.first().simpleName
                    GlobalArtist.printText(
                        name,
                        aircraft.domain.x,
                        aircraft.domain.y - Node_cu_AircraftGroup.DISTANCE_TO_BE_GROUP
                    )
                }
            }
    }
}