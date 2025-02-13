package com.example.myapplication

import android.os.Bundle

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlin.math.roundToInt


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {


                Unitconver()


                }
        }
    }


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
   }
@Composable
fun Unitconver(){
    var inputvalue by remember { mutableStateOf(" ") }
    var outputvalue by remember { mutableStateOf("") }
    var inputunit by remember { mutableStateOf("cm") }
    var outputinput by remember { mutableStateOf("m") }
    var iexpanded by remember { mutableStateOf(false) }
    var oexpanded by remember { mutableStateOf(false) }
    val conversionfactor= remember { mutableStateOf(0.01) }

    fun convertunits(){
      val  inputvalueDouble =inputvalue.toDoubleOrNull()?:0.0
        var result=(inputvalueDouble*conversionfactor.value*100.0).roundToInt()/100.0
        outputvalue=result.toString()
    }

    val context= LocalContext.current
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,


    ) {

      Text(text = "unit converter")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputvalue, onValueChange ={ inputvalue=it   },  label = { Text("Input Value") },

        )
    /*    Button(onClick ={Toast.makeText(context,
            "thanks for clicking",
            Toast.LENGTH_LONG).show() } )
        {
            Text("click me")
        }*/
        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Box{
                Button(onClick = {iexpanded=true}) {
                    Text(inputunit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")

                }
                DropdownMenu(expanded = iexpanded, onDismissRequest ={ iexpanded=false} ) {
                    DropdownMenuItem(text = { Text("cms") } , onClick = {
                        iexpanded=false
                        inputunit="cm"
                        conversionfactor.value=0.01
                        convertunits()

                    } )
                    DropdownMenuItem(text = { Text("ms") } , onClick = {
                        iexpanded=false
                        inputunit="ms"
                        conversionfactor.value=1.0
                        convertunits()
                    } )
                    DropdownMenuItem(text = { Text("feet") } , onClick = {
                        iexpanded=false
                        inputunit="feet"
                        conversionfactor.value=0.3048
                        convertunits()
                    } )
                    DropdownMenuItem(text = { Text("milli") } , onClick = {
                        iexpanded=false
                        inputunit="milli"
                        conversionfactor.value=0.001
                        convertunits()
                    } )


            }

            }
            Spacer(modifier = Modifier.width(32.dp))

            Box{
                Button(onClick = {oexpanded=true}) {
                    Text(outputinput)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")

                }
                DropdownMenu(expanded = oexpanded, onDismissRequest ={oexpanded=false } ) {
                    DropdownMenuItem(text = { Text("cms") } , onClick = {
                        oexpanded=false
                        inputunit="cm"
                        conversionfactor.value=0.01
                        convertunits()
                    } )
                    DropdownMenuItem(text = { Text("ms") } , onClick = {
                        oexpanded=false
                        inputunit="ms"
                        conversionfactor.value=1.00
                        convertunits()
                    } )
                    DropdownMenuItem(text = { Text("feet") } , onClick = {
                        oexpanded=false
                        inputunit="feet"
                        conversionfactor.value=0.03048
                        convertunits()
                    } )
                    DropdownMenuItem(text = { Text("milli") } , onClick = {
                        iexpanded=false
                        inputunit="milli"
                        conversionfactor.value=0.001
                        convertunits()
                    } )


                }


            }

                  }
        Text("Result: $outputvalue ",
        )

    }

}