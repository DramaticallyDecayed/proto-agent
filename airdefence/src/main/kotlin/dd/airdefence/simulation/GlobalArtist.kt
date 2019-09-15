package dd.airdefence.simulation

import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer

object GlobalArtist {

    lateinit var drawer: Drawer


    fun drawGroup(x: Double, y: Double, radius:Double){
        drawer.fill = null
        drawer.stroke = ColorRGBa.WHITE
        drawer.strokeWeight = 1.0
        drawer.circle(x, y, radius)
    }
}