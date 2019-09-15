package dd.airdefence

import dd.airdefence.perception.FilePerception
import dd.airdefence.sas.SAS
import java.io.FileNotFoundException

object Main {

    @Throws(FileNotFoundException::class)
    @JvmStatic
    fun main(args: Array<String>) {
       SAS(FilePerception()).cycle()
    }

}