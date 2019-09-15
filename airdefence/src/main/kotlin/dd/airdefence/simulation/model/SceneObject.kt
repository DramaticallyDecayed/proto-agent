package dd.airdefence.simulation.model

import dd.sas.presentation.WorldEntity
import org.openrndr.draw.Drawer

abstract class SceneObject(var x: Double, var y: Double) {
    abstract fun update(): SceneObject
    abstract fun draw(drawer: Drawer)
    abstract fun radarSignature(): WorldEntity?
    fun type() = this::class.java.simpleName
    open fun isDestroyed() = false
}