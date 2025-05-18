import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.BufferedReader
import java.io.InputStreamReader

data class Operation(val operation: String, val unitCost: Double, val quantity: Int)

fun main() {
    val mapper = jacksonObjectMapper()
    val reader = BufferedReader(InputStreamReader(System.`in`))
    
    reader.lineSequence().forEach { line ->
        if (line.isNotBlank()) {
            val operations: List<Operation> = mapper.readValue(line)
            val taxes = calculateTaxes(operations)
            val result = mapper.writeValueAsString(taxes)
            println(result)
        }
    }
}

fun calculateTaxes(operations: List<Operation>): List<Map<String, Double>> {
    val results = mutableListOf<Map<String, Double>>()
    var totalQuantity = 0
    var totalCost = 0.0
    var accumulatedLoss = 0.0

    for (operation in operations) {
        when (operation.operation) {
            "buy" -> {
                totalQuantity += operation.quantity
                totalCost += operation.unitCost * operation.quantity
                results.add(mapOf("tax" to 0.0))
            }
            "sell" -> {
                val totalSale = operation.unitCost * operation.quantity
                if (totalSale <= 20000) {
                    results.add(mapOf("tax" to 0.0))
                } else {
                    val averageCost = totalCost / totalQuantity
                    val profit = (operation.unitCost - averageCost) * operation.quantity
                    if (profit > 0) {
                        val tax = profit * 0.20
                        results.add(mapOf("tax" to tax))
                        accumulatedLoss = 0.0
                    } else {
                        accumulatedLoss += -profit
                        results.add(mapOf("tax" to 0.0))
                    }
                }
            }
        }
    }
    return results
}