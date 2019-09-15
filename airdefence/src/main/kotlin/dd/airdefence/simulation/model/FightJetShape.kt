package dd.airdefence.simulation.model

import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import org.openrndr.math.Vector2
import org.openrndr.shape.contour

class FightJetShape(
    val fightJet: FightJet,
    private val color: ColorRGBa
) {

    var x = fightJet.x
    var y = fightJet.y
    var head = fightJet.head

    companion object {
        const val width = 20.0
        const val height = 30.0
    }

    fun draw(drawer: Drawer) {
        x = fightJet.x
        y = fightJet.y
        drawer.fill = color
        drawer.stroke = null

        drawer.translate(x + width / 2.0, y)
        drawer.rotate(Math.toDegrees(fightJet.head - head))
        drawer.translate(0.0 - width / 2.0, 0.0)

        drawer.contour(
            contour {
                moveTo(Vector2(0.0, 0.0))
                lineTo(cursor + Vector2(width, 0.0))
                lineTo(cursor + Vector2(-width / 2, height))
                lineTo(anchor)
                close()
            }
        )
        drawer.reset()

    }


}