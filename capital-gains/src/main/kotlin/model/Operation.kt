package model

data class Operation(
    val operation: String,
    val unitCost: Double,
    val quantity: Int
) {
    fun calculateWeightedAverage(currentAverage: Double, currentQuantity: Int): Double {
        return ((currentQuantity * currentAverage) + (quantity * unitCost)) / (currentQuantity + quantity)
    }

    fun calculateTax(sellPrice: Double, weightedAverage: Double): Double {
        val profit = (sellPrice - weightedAverage) * quantity
        return if (profit > 0) {
            profit * 0.20
        } else {
            0.0
        }
    }
}