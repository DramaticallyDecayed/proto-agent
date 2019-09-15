package dd.airdefence.simulation

import dd.airdefence.sas.SAS
import dd.airdefence.simulation.model.CoveredObject
import dd.airdefence.simulation.model.FightJet
import dd.airdefence.simulation.model.FlightCommand
import dd.airdefence.simulation.model.Radar
import org.openrndr.application
import kotlin.math.PI
import kotlin.math.abs

fun main(args: Array<String>) =
    application {

        configure {
            width = 1200
            height = 960
        }


        program {

            GlobalArtist.drawer = drawer

            val firstImportantObjectX = width / 8.0
            val firstImportantObjectY = height - 100.0
            val secondImportantObjectX = width - width / 8.0
            val secondImportantObjectY = height - 100.0
            val radar = Radar(width / 2.0, height - 100.0, height.toDouble(), this)
            val scene = listOf(
                FightJet(
                    1,
                    width / 2.0,
                    0.0,
                    listOf(
                        FlightCommand(PI / 2) { _, _ -> true },
                        FlightCommand(PI - PI / 12) { _, y -> y > 150.0 },
                        FlightCommand(PI / 2) { x, _ -> x < firstImportantObjectX },
                        FlightCommand(PI / 2) { _, y -> abs(y - firstImportantObjectY) < 50.0 }
                    ),
                    false
                ),
                FightJet(
                    2,
                    width / 2.0,
                    50.0,
                    listOf(
                        FlightCommand(PI / 2) { _, _ -> true },
                        FlightCommand(PI - PI / 12) { _, y -> y > 210.0 },
                        FlightCommand(PI / 2) { x, _ -> x < firstImportantObjectX },
                        FlightCommand(PI / 2) { _, y -> abs(y - firstImportantObjectY) < 50.0 }
                    ),
                    false),
                FightJet(
                    3,
                    width / 2.0 - 20.0,
                    25.0,
                    listOf(
                        FlightCommand(PI / 2) { _, _ -> true },
                        FlightCommand(PI / 12) { _, y -> y > 200.0 },
                        FlightCommand(PI / 2) { x, _ -> x > secondImportantObjectX },
                        FlightCommand(PI / 2) { _, y -> abs(y - secondImportantObjectY) < 50.0 }
                    ),
                    false
                ),
                FightJet(
                    4,
                    width / 2.0 + 20.0,
                    25.0,
                    listOf(
                        FlightCommand(PI / 2) { _, _ -> true },
                        FlightCommand(PI / 12) { _, y -> y > 200.0 },
                        FlightCommand(PI / 2) { x, _ -> x > secondImportantObjectX },
                        FlightCommand(PI / 2) { _, y -> abs(y - secondImportantObjectY) < 50.0 }
                    ),
                    false
                ),
                CoveredObject(1, firstImportantObjectX, firstImportantObjectY, true),
                CoveredObject(2, secondImportantObjectX, secondImportantObjectY, true),
                radar
            )


            val sas = SAS(SimulationPerception(scene, radar))

            extend {
                scene
                    .filterNot { it.isDestroyed() }
                    .map { it.update() }
                    .forEach { it.draw(drawer) }
                sas.cycle()
            }
        }
    }

