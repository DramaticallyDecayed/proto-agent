package dd.airdefence.simulation

import dd.airdefence.sas.SAS
import dd.airdefence.simulation.model.CoveredObject
import dd.airdefence.simulation.model.FightJet
import dd.airdefence.simulation.model.FlightCommand
import dd.airdefence.simulation.model.Radar
import org.openrndr.application
import kotlin.math.PI

fun main(args: Array<String>) =
    application {

        configure {
            width = 1000
            height = 960
        }


        program {

            GlobalArtist.drawer = drawer

            val firstImportantObjectX = width / 8.0
            val secondImportantObjectX = width - width / 8.0

            val scene = listOf(
                FightJet(
                    1,
                    width / 2.0,
                    100.0,
                    listOf(
                        FlightCommand(PI / 2) { _, _ -> true },
                        FlightCommand(PI - PI / 12) { _, y -> y > 150.0 },
                        FlightCommand(PI / 2) { x, _ -> x < firstImportantObjectX }
                    ),
                    false
                ),
                FightJet(
                    2,
                    width / 2.0,
                    150.0,
                    listOf(
                        FlightCommand(PI / 2) { _, _ -> true },
                        FlightCommand(PI - PI / 12) { _, y -> y > 200.0 },
                        FlightCommand(PI / 2) { x, _ -> x < firstImportantObjectX }
                    ),
                    false),
                FightJet(
                    3,
                    width / 2.0 - 20.0,
                    125.0,
                    listOf(
                        FlightCommand(PI / 2) { _, _ -> true },
                        FlightCommand(PI / 12) { _, y -> y > 200.0 },
                        FlightCommand(PI / 2) { x, _ -> x > secondImportantObjectX }
                    ),
                    false
                ),
                FightJet(
                    4,
                    width / 2.0 + 20.0,
                    125.0,
                    listOf(
                        FlightCommand(PI / 2) { _, _ -> true },
                        FlightCommand(PI / 12) { _, y -> y > 200.0 },
                        FlightCommand(PI / 2) { x, _ -> x > secondImportantObjectX }
                    ),
                    false
                ),
                CoveredObject(1, firstImportantObjectX, height - 100.0, true),
                CoveredObject(2, secondImportantObjectX, height - 100.0, true),
                Radar(width / 2.0, height - 100.0, height.toDouble(), this)
            )


            val sas = SAS(SimulationPerception(scene))

            extend {
                scene
                    .map { it.update() }
                    .forEach { it.draw(drawer) }
                sas.cycle()
            }
        }
    }

