package com.example.calc

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun CalcButton(
    symbol: String,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(CircleShape) // Make the button circular
            .background(color) // Set background color
            .clickable { onClick() } // Handle click action
            .then(modifier) // Allow for additional modifier customization

    ) {
        Text(
            text = symbol,
            fontSize = 36.sp, // Set the font size
            color = Color.Black, // Set the text color
            style = TextStyle(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold) // Optional: Set the font weight
        )
    }
}
