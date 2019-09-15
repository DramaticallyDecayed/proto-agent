package dd.airdefence.simulation.model

import dd.airdefence.sas.worldentity.ImportantObjectC
import dd.sas.presentation.WorldEntity
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer

class CoveredObject(
    val id: Int,
    x: Double,
    y: Double,
    ally: Boolean
) : SceneObject(x, y) {

    override fun radarSignature(): WorldEntity {
        val importantObject = ImportantObjectC()
        importantObject.id = id
        importantObject.x = x.toInt()
        importantObject.y = y.toInt()
        return importantObject
    }

    companion object {
        const val height = 50.0
        const val width = 50.0
    }

    private val importantObjectShape =
        ImportantObjectShape(this, if (ally) ColorRGBa.RED else ColorRGBa.BLUE)

    override fun draw(drawer: Drawer) {
        importantObjectShape.draw(drawer)
    }

    override fun update(): SceneObject {
        return this
    }
}