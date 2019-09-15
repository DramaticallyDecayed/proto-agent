package dd.airdefence.simulation.model

import org.openrndr.Program
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import org.openrndr.shape.Circle

class RadarShape(
    val radar: Radar,
    private val color: ColorRGBa,
    val program: Program
) {
    fun draw(drawer: Drawer) {
        drawer.fill = color
        drawer.stroke = null
        drawer.strokeWeight = 1.0
        drawer.circle(radar.x, radar.y, radar.radarSize)


        drawer.stroke = ColorRGBa.GREEN.opacify(0.1)
        drawer.strokeWeight = radar.radarArea
        val sub1 = Circle(radar.x, radar.y, radar.radarArea).contour.sub(0.02, 0.48)
        drawer.contour(sub1)
    }
}