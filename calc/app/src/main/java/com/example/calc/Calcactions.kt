package com.example.calc

sealed class CalcActions {
    // Action for number input
    data class Num(val num: Int) : CalcActions()

    // Action for clearing the calculator state
    object Clear : CalcActions()

    // Action for deleting the last digit or operation
    object Delete : CalcActions()

    // Action for decimal point
    object Decimal : CalcActions()

    // Action to perform the calculation
    object Calculate : CalcActions()

    // Action for choosing an operation (like +, -, *, /)
    data class Operation(val operation: Operations) : CalcActions()
}
