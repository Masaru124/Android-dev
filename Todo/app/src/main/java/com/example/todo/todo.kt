package com.example.todo

data class TodoItem(val id: Int, val title: String, var isCompleted: Boolean = false)
