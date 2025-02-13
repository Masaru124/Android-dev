package com.example.counter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.counter.ui.theme.CounterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel:CounterViewModel=viewModel()
            CounterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(

                        modifier = Modifier.padding(innerPadding),
                        viewModel()


                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier, viewModel:CounterViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text("count:${viewModel.count.value}",fontSize = 24.sp ,
            fontWeight = FontWeight.Bold)

        Spacer(modifier=Modifier.height(16.dp))

        Button(onClick = { viewModel.incr() }, modifier = Modifier.fillMaxWidth(0.4f).height(50.dp)) {
            Text("increament",
                fontSize = 16.sp
            )
        }
        Spacer(modifier=Modifier.height(16.dp))

        Button(onClick = { viewModel.decrement() }, modifier = Modifier.fillMaxWidth(0.4f).height(50.dp)) {
            Text("decreament", fontSize = 16.sp)
        }
        Spacer(modifier=Modifier.height(16.dp))

        Button(onClick = { viewModel.reset() }, modifier = Modifier.fillMaxWidth(0.4f).height(50.dp)) {
            Text("reset", fontSize = 16.sp)
        }


    }
}

