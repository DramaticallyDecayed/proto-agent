package dd.airdefence.simulation.model

data class FlightCommand(
    val head: Double,
    val condition: (Double, Double) -> Boolean
)