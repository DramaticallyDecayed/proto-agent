package dd.airdefence.simulation

import common.Subscriber
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import org.openrndr.draw.FontImageMap

object GlobalArtist {


    lateinit var drawer: Drawer
    lateinit var font: FontImageMap

    var width = 0.0
    var height = 0.0

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


    val hierarchy = mutableMapOf<String, Triple<Int, Double, Double>>()

    fun drawHierarchy(nodeName: String, level: Int, subscribers: List<String> = emptyList()) {

        drawer.fontMap = font
        drawer.fill = ColorRGBa.YELLOW
        drawer.stroke = ColorRGBa.YELLOW
        drawer.strokeWeight = 1.0
        val radius = 20.0


        if (hierarchy.containsKey(nodeName)) {
            val x = hierarchy[nodeName]!!.second
            val y = hierarchy[nodeName]!!.third
            drawer.text(nodeName, x - (nodeName.length * 4.0), y - radius)
            drawer.circle(x, y, radius)
            link(x,y, subscribers)
        } else {
            val onTheLevel = hierarchy.filter { it.value.first == level }
            if (onTheLevel.isEmpty()) {
                val x = width - width / 8 - ((level-1)%2 * 10)
                val y = radius * 2.0 * ((level) * 1.5)
                drawer.text(nodeName, x - nodeName.length * 4.0, y - radius)
                drawer.circle(x, y, radius)
                hierarchy[nodeName] = Triple(level, x, y)
                link(x,y, subscribers)
            } else {
                val x = width - width / 8 - onTheLevel.keys.last().length * 4.0 - nodeName.length * 4.0 - 20.0 - ((level-1)%2 * 10)
                val y = radius * 2.0 * ((level) * 1.5)
                drawer.text(nodeName, x - nodeName.length * 4.0, y - radius)
                drawer.circle(x, y, radius)
                hierarchy[nodeName] = Triple(level, x, y)
                link(x,y, subscribers)
            }
        }
    }

    private fun link(x: Double, y: Double, subscribers: List<String> ){
        subscribers
            .forEach { subscriber ->
                hierarchy[subscriber]
                    ?.also {
                        drawer.lineSegment(x, y, it.second, it.third)
                    }
            }
    }
}