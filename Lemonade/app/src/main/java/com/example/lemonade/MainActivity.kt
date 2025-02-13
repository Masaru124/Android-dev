package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Lemonade(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Lemonade(modifier: Modifier){
    var curr by remember { mutableStateOf(1) }
    var taps by remember { mutableStateOf(0) }
when(curr){
    1-> lemons(R.drawable.lemon_tree,
        "This is a lemon tree ",
        onImageClick = {
            curr=2
        taps=(2..4).random()

        })
    2-> lemons(R.drawable.lemon_squeeze,
            "Lemons",
            onImageClick = {
                taps--
                if(taps==0){
                    curr=3
                }
            }
            )

    3-> lemons(R.drawable.lemon_drink,
            "lemonade",
            onImageClick = {
                curr=4
            }

            )
    4-> lemons(R.drawable.lemon_restart,
            "restart",
            onImageClick = {
                curr=1
            }

            )

}



}
@Composable
fun lemons(drawableResourceId: Int,
           contentDescriptionResourceId: String,
           onImageClick: () -> Unit,
           modifier: Modifier = Modifier

)
{
    Box(modifier = Modifier){
        Column ( horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()){

            Button(onClick = {onImageClick()}, shape = RoundedCornerShape(5.dp)) {
                Image(painter = painterResource(drawableResourceId), contentDescription = (contentDescriptionResourceId.toString()))

                
            }

        }

    }
}