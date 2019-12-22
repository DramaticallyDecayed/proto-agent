package dd.airdefence.sas

import dd.airdefence.implementation.Perceptor2SASAdapterImpl
import dd.airdefence.ontology.OntologyHandler
import dd.airdefence.perception.APerception
import dd.sas.SAS

class SAS(val perception: APerception) {
    private val ontologyHandler = OntologyHandler()
    val sas = SAS(ontologyHandler)
    private val adapter = initPerceptor2SASAdapter(sas)

    init {
        ontologyHandler.arm()
        sas.cycle()
    }

    fun cycle() {
//        while (true) {
        perception.cycle()
        adapter.importantObjectList = perception.importantObjects
        adapter.unknownAircraftList = perception.unknownAircrafts
        sas.cycle()
//        }
    }

    private fun initPerceptor2SASAdapter(sas: SAS): Perceptor2SASAdapterImpl {
        val adapter = Perceptor2SASAdapterImpl()
        adapter.level.addNode(adapter)
        sas.levelHolder.addLevel(adapter.level)
        return adapter
    }

}