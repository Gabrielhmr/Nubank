package org.example.core

import org.example.data.OperationInput

interface TaxCalculator {
    fun execute(operationInputs: List<OperationInput>): List<Map<String, Double>>
}