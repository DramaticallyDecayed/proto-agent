package dd.airdefence.simulation

import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import org.openrndr.draw.loadFont

object GlobalArtist {

    val font = loadFont(
        GlobalArtist::class.java.classLoader.getResource("fonts/iosevka-custom-medium.ttf").path,
        20.0
    )

    lateinit var drawer: Drawer


    fun drawGroup(x: Double, y: Double, radius: Double) {
        drawer.fill = null
        drawer.stroke = ColorRGBa.WHITE
        drawer.strokeWeight = 1.0
        drawer.circle(x, y, radius)
    }

    fun drawDanger(x: Double, y: Double, radius: Double, originX: Double, originY: Double) {
        drawer.fill = null
        drawer.stroke = ColorRGBa.RED
        drawer.strokeWeight = 1.0
        drawer.circle(x, y, radius)
        drawer.lineSegment(originX, originY, x, y - radius)
    }

    fun printText(text: String, x: Double, y: Double) {
        drawer.fontMap = font
        drawer.fill = ColorRGBa.WHITE
        drawer.text(text, x, y)
    }
}