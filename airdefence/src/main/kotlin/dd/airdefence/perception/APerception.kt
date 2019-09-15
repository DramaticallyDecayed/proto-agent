package dd.airdefence.perception

import dd.airdefence.sas.worldentity.ImportantObject
import dd.airdefence.sas.worldentity.UnknownAircraft

abstract class APerception(
    val importantObjects: MutableList<ImportantObject>,
    val unknownAircrafts: MutableList<UnknownAircraft>
) {
    abstract fun cycle()
}