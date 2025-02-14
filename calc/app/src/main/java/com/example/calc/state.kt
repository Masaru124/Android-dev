package com.example.calc

data class CalculatorState(
    val num1: String = "",
    val num2: String = "",
    val operations: Operations? = null
) {
    init {
        require(num1.length <= MAX_DIGITS) { "Number 1 exceeds maximum digits" }
        require(num2.length <= MAX_DIGITS) { "Number 2 exceeds maximum digits" }
    }
    
    companion object {
        const val MAX_DIGITS = 15
    }
}
