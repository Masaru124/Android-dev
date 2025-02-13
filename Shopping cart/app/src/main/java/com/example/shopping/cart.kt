package com.example.shopping

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Define the ShoppingItem data class
data class ShoppingItem(val id: Int, var name: String, var quantity: Int, var isEditing: Boolean = false)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cart(modifier: Modifier = Modifier) {
    var sitems by remember { mutableStateOf(listOf<ShoppingItem>()) }
    var showDialog by remember { mutableStateOf(false) }
    var itemName by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }

    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Button(onClick = { showDialog = true }, modifier = Modifier.align(Alignment.CenterHorizontally).padding(16.dp)) {
            Text("Add Item")
        }

        LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            items(sitems) { item ->
                if(item.isEditing) {
                    ShoppingItemRow(item = item, onEditClick = { editedName, editedQuantity ->
                        sitems = sitems.map {
                            if (it.id == item.id)
                                it.copy(name = editedName, quantity = editedQuantity, isEditing = false)
                            else
                                it
                        }
                    }, onDeleteClick = {
                        sitems = sitems.filterNot { it.id == item.id }
                    })
                } else {
                    ShoppingItemRow(item = item, onEditClick = {
                        sitems = sitems.map {
                            if (it.id == item.id)
                                it.copy(isEditing = true)
                            else
                                it
                        }
                    }, onDeleteClick = {
                        sitems = sitems.filterNot { it.id == item.id }
                    })
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            if (itemName.isNotBlank() && quantity.isNotBlank() && quantity.toIntOrNull() != null) {
                                val newItem = ShoppingItem(
                                    id = sitems.size + 1,
                                    name = itemName,
                                    quantity = quantity.toInt()
                                )
                                sitems = sitems + newItem
                                showDialog = false
                                itemName = ""
                                quantity = ""
                            }
                        }
                    ) {
                        Text("Add")
                    }
                    Button(onClick = { showDialog = false }) {
                        Text("Cancel")
                    }
                }
            },
            text = {
                Column {
                    Text("Item Name:")
                    TextField(value = itemName, onValueChange = { itemName = it })
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Quantity:")
                    TextField(value = quantity, onValueChange = { quantity = it })
                }
            }
        )
    }
}

// Define the ShoppingItemRow composable
@Composable
fun ShoppingItemRow(
    item: ShoppingItem,
    onEditClick: (String, Int) -> Unit,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(shape = RoundedCornerShape(20), border = BorderStroke(2.dp, Color(0XFF018786)))
    ) {
        Column(modifier = Modifier.weight(1f)) {
            if (item.isEditing) {
                // Show TextFields for editing
                var editedName by remember { mutableStateOf(item.name) }
                var editedQuantity by remember { mutableStateOf(item.quantity.toString()) }

                TextField(
                    value = editedName,
                    onValueChange = { editedName = it },
                    label = { Text("Name") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = editedQuantity,
                    onValueChange = { editedQuantity = it },
                    label = { Text("Quantity") }
                )
                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = {
                    val newQuantity = editedQuantity.toIntOrNull() ?: 0
                    if (editedName.isNotBlank() && newQuantity > 0) {
                        onEditClick(editedName, newQuantity) // Pass edited name and quantity
                    }
                }) {
                    Text("Save")
                }
            } else {
                // Show regular text if not editing
                Text(text = item.name, modifier = Modifier.padding(8.dp))
                Text(text = "Qty: ${item.quantity}", modifier = Modifier.padding(8.dp))
            }
        }

        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.align(Alignment.CenterVertically)) {
            if (!item.isEditing) {
                Button(onClick = { onEditClick(item.name, item.quantity) }, modifier = Modifier.padding(4.dp)) {
                    Text("Edit")
                }
            }
            Button(onClick = onDeleteClick, modifier = Modifier.padding(4.dp)) {
                Text("Delete")
            }
        }
    }
}
