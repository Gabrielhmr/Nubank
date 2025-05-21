package org.example.data

data class SellResultEntity(
    val totalQuantity: Int,
    val totalCost: Double,
    val accumulatedLoss: Double,
    val tax: Double
)