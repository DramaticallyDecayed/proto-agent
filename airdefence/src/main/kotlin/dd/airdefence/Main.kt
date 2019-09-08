package dd.airdefence

import dd.airdefence.implementation.Perceptor2SASAdapterImpl
import dd.airdefence.ontology.OntologyHandler
import dd.sas.SAS
import java.io.FileNotFoundException

object Main {

    @Throws(FileNotFoundException::class)
    @JvmStatic
    fun main(args: Array<String>) {


        val ontologyHandler = OntologyHandler()
        ontologyHandler.arm()

        val sas = SAS(ontologyHandler)
        val adapter = initPerceptor2SASAdapter(sas)

        val perception = Perception()

        while (true) {
            perception.cycle()
            adapter.importantObjectList = perception.importantObjects
            adapter.unknownAircraftList = perception.unknownAircraft
            sas.cycle()
        }

    }

    private fun initPerceptor2SASAdapter(sas: SAS): Perceptor2SASAdapterImpl {
        val adapter = Perceptor2SASAdapterImpl()
        adapter.level.addNode(adapter)
        sas.levelHolder.addLevel(adapter.level)
        return adapter
    }
}