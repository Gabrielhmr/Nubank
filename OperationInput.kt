package org.example.data

import com.fasterxml.jackson.annotation.JsonProperty

data class OperationInput(
    val operation: String,
    @JsonProperty("unit-cost")
    val unitCost: Double,
    val quantity: Int
)