package org.example.core

import org.example.data.OperationInput
import org.example.data.OperationTypeEnum
import org.example.data.SellResultEntity

class CalculateTaxesUseCase : TaxCalculator {
    override fun execute(operationInputs: List<OperationInput>): List<Map<String, Double>> {
        val results = mutableListOf<Map<String, Double>>()
        var totalQuantity = 0
        var totalCost = 0.0
        var accumulatedLoss = 0.0

        for (operation in operationInputs) {
            when (operation.operation.uppercase()) {
                OperationTypeEnum.BUY.name -> {
                    val (newQuantity, newCost) = processBuy(totalQuantity, totalCost, operation)
                    totalQuantity = newQuantity
                    totalCost = newCost
                    results.add(mapOf("tax" to 0.0))
                }
                OperationTypeEnum.SELL.name -> {
                    val sellResult = processSell(totalQuantity, totalCost, accumulatedLoss, operation)
                    totalQuantity = sellResult.totalQuantity
                    totalCost = sellResult.totalCost
                    accumulatedLoss = sellResult.accumulatedLoss
                    results.add(mapOf("tax" to sellResult.tax))
                }
            }
        }
        return results
    }

    private fun processBuy(
        totalQuantity: Int,
        totalCost: Double,
        operationInput: OperationInput
    ): Pair<Int, Double> {
        val newQuantity = totalQuantity + operationInput.quantity
        val newCost = totalCost + operationInput.unitCost * operationInput.quantity
        return Pair(newQuantity, newCost)
    }

    private fun processSell(
        totalQuantity: Int,
        totalCost: Double,
        accumulatedLoss: Double,
        operationInput: OperationInput
    ): SellResultEntity {
        val totalSale = operationInput.unitCost * operationInput.quantity
        val averageCost = totalCost / totalQuantity
        val profit = (operationInput.unitCost - averageCost) * operationInput.quantity

        var newTotalQuantity = totalQuantity - operationInput.quantity
        var newTotalCost = totalCost - averageCost * operationInput.quantity
        var newAccumulatedLoss = accumulatedLoss
        var tax = 0.0

        if (profit < 0) {
            newAccumulatedLoss += -profit
        } else if (totalSale > 20000) {
            val netProfit = if (newAccumulatedLoss > 0) {
                val usedLoss = minOf(newAccumulatedLoss, profit)
                newAccumulatedLoss -= usedLoss
                profit - usedLoss
            } else {
                profit
            }
            if (netProfit > 0) {
                tax = (netProfit * 0.20 * 100).toInt() / 100.0
            }
        }

        return SellResultEntity(newTotalQuantity, newTotalCost, newAccumulatedLoss, tax)
    }
}