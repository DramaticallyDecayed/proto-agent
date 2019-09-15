package dd.airdefence.simulation.model

import dd.sas.presentation.WorldEntity
import org.openrndr.Program
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer

class Radar(
    x: Double,
    y: Double,
    height: Double,
    program: Program
) : SceneObject(x, y) {

    override fun radarSignature(): WorldEntity? {
        return null
    }

    val radarSize = 30.0
    val radarArea = height / 2.5 * 1.8

    val radarShape = RadarShape(this, ColorRGBa.GREEN, program)

    override fun update(): SceneObject {
        return this
    }

    override fun draw(drawer: Drawer) {
        radarShape.draw(drawer)
    }
}