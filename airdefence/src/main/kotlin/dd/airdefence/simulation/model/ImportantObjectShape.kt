package dd.airdefence.simulation.model

import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer

class ImportantObjectShape(
    val importantObject: CoveredObject,
    private val color: ColorRGBa
) {
    fun draw(drawer: Drawer) {
        drawer.fill = color
        drawer.stroke = null
        drawer.rectangle(
            importantObject.x,
            importantObject.y,
            CoveredObject.width,
            CoveredObject.height
        )
    }
}