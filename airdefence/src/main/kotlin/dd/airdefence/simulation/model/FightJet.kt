package dd.airdefence.simulation.model

import dd.airdefence.sas.worldentity.UnknownAircraftC
import dd.sas.presentation.WorldEntity
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import kotlin.math.cos
import kotlin.math.sin

class FightJet(
    val id: Int,
    x: Double,
    y: Double,
    val flightProgram: List<FlightCommand>,
    ally: Boolean
) : SceneObject(x, y) {


    override fun radarSignature(): WorldEntity {
        val unknownAircraft = UnknownAircraftC()
        unknownAircraft.x = x.toInt()
        unknownAircraft.y = y.toInt()
        unknownAircraft.vx = (velocity * cos(head)).toInt()
        unknownAircraft.vx = (velocity * sin(head)).toInt()
        unknownAircraft.id = id
        unknownAircraft.rcs = 5
        return unknownAircraft
    }

    var velocity = 0.5
    var flightProgramPointer = 0
    var head = flightProgram[flightProgramPointer++].head

    private val fightJetShape =
        FightJetShape(this, if (ally) ColorRGBa.RED else ColorRGBa.BLUE)

    override fun update(): SceneObject {
        rotate()
        move()
        return this
    }

    private fun rotate() {
        if (flightProgram.size > flightProgramPointer) {
            if (flightProgram[flightProgramPointer].condition(x, y)) {
                head = flightProgram[flightProgramPointer].head
                flightProgramPointer++
            }
        }
    }

    private fun move() {
        x += velocity * cos(head)
        y += velocity * sin(head)
    }

    override fun draw(drawer: Drawer) {
        fightJetShape.draw(drawer)
    }

}