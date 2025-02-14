package com.example.calc

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    var state by mutableStateOf(CalculatorState())
        private set
    
    private val MAX_DIGITS = 15

    fun onAction(action: CalcActions) {
        when (action) {
            is CalcActions.Num -> handleNumber(action.num)
            is CalcActions.Operation -> handleOperation(action.operation)
            CalcActions.Clear -> clear()
            CalcActions.Delete -> delete()
            CalcActions.Decimal -> handleDecimal()
            CalcActions.Calculate -> calculate()
        }
    }

    private fun handleNumber(num: Int) {
        if (state.operations == null) {
            if (state.num1.length < MAX_DIGITS) {
                state = state.copy(num1 = state.num1 + num.toString())
            }
        } else {
            if (state.num2.length < MAX_DIGITS) {
                state = state.copy(num2 = state.num2 + num.toString())
            }
        }
    }

    private fun handleOperation(operation: Operations) {
        if (state.num1.isNotEmpty()) {
            state = state.copy(operations = operation)
        }
    }

    private fun clear() {
        state = CalculatorState()
    }

    private fun delete() {
        if (state.num2.isNotEmpty()) {
            state = state.copy(num2 = state.num2.dropLast(1))
        } else if (state.operations != null) {
            state = state.copy(operations = null)
        } else if (state.num1.isNotEmpty()) {
            state = state.copy(num1 = state.num1.dropLast(1))
        }
    }

    private fun handleDecimal() {
        if (state.operations == null) {
            if (!state.num1.contains(".") && state.num1.length < MAX_DIGITS) {
                state = state.copy(num1 = state.num1 + ".")
            }
        } else {
            if (!state.num2.contains(".") && state.num2.length < MAX_DIGITS) {
                state = state.copy(num2 = state.num2 + ".")
            }
        }
    }

    private fun calculate() {
        if (state.num1.isNotEmpty() && state.num2.isNotEmpty() && state.operations != null) {
            try {
                val num1 = state.num1.toDouble()
                val num2 = state.num2.toDouble()
                
                val result = when (state.operations) {
                    Operations.Add -> num1 + num2
                    Operations.Subtract -> num1 - num2
                    Operations.Multiply -> num1 * num2
                    Operations.Divide -> {
                        if (num2 == 0.0) {
                            state = state.copy(num1 = "Error", num2 = "", operations = null)
                            return
                        }
                        num1 / num2
                    }
                    null -> 0.0
                }
                
                val formattedResult = if (result % 1 == 0.0) {
                    result.toLong().toString()
                } else {
                    result.toString()
                }
                
                state = state.copy(
                    num1 = formattedResult.take(MAX_DIGITS),
                    num2 = "",
                    operations = null
                )
            } catch (e: NumberFormatException) {
                state = state.copy(num1 = "Error", num2 = "", operations = null)
            }
        }
    }
}
