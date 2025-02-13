package com.example.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todo.ui.theme.TodoTheme



// ViewModel for managing the Todo list
class TodoViewModel : ViewModel() {
    private val _todos = mutableStateListOf<TodoItem>()
    val todos: List<TodoItem> by mutableStateOf(_todos)

    fun add(title: String) {
        val newTodo = TodoItem(id = _todos.size + 1, title = title, isCompleted = false)
        _todos.add(newTodo)
    }

    fun toggle(id: Int) {
        val index = _todos.indexOfFirst { it.id == id }
        if (index != -1) {
            val oldItem = _todos[index]
            _todos[index] = oldItem.copy(isCompleted = !oldItem.isCompleted)
        }
    }

    fun delete(id: Int) {
        _todos.removeIf { it.id == id }
    }
}

// Main activity to set the content of the app
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoApp()
        }
    }
}

// Main Composable for the Todo app
@Composable
fun TodoApp(todoViewModel: TodoViewModel = viewModel()) {
    var text by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        // App Title
        Text(text = "To-Do List", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // Input field for adding a new Todo
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (text.isNotBlank()) {
                        todoViewModel.add(text)
                        text = ""
                    }
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            label = { Text("Add a to-do") }
        )

        // Add Todo button
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                if (text.isNotBlank()) {
                    todoViewModel.add(text)
                    text = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Todo")
        }

        // To-Do List
        Spacer(modifier = Modifier.height(16.dp))
        TodoList(todoViewModel)
    }
}

// Composable for the Todo list
@Composable
fun TodoList(todoViewModel: TodoViewModel) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(todoViewModel.todos, key = { it.id }) { todo ->
            TodoItemView(todo, todoViewModel)
        }
    }
}

// Composable for individual Todo item in the list
@Composable
fun TodoItemView(todo: TodoItem, todoViewModel: TodoViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = todo.title, modifier = Modifier.weight(1f))

        // Checkbox to mark the Todo as completed
        Checkbox(
            checked = todo.isCompleted,
            onCheckedChange = {
                todoViewModel.toggle(todo.id)
            }
        )

        // Delete button
        IconButton(onClick = { todoViewModel.delete(todo.id) }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
        }
    }
}

// Preview function to display the UI in the Android Studio Preview
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TodoTheme {
        TodoApp()
    }
}
