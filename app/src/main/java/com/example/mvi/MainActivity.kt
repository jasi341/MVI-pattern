package com.example.mvi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvi.ui.theme.MVITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
         val viewModel: CounterViewModel by viewModels()
        super.onCreate(savedInstanceState)
        setContent {
            MVITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CounterApp(viewModel)
                }
            }
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
    MVITheme {
        Greeting("Android")
    }
}

@Composable
fun CounterView(model: CounterModel, onIncrement: () -> Unit, onDecrement: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Count: ${model.count}", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(onClick = onIncrement) {
                Text("Increment")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = onDecrement) {
                Text("Decrement")
            }
        }
    }
}
@Composable
fun CounterApp(viewModel: CounterViewModel) {
    val model = viewModel.model.observeAsState()

    CounterView(
        model = model.value ?: CounterModel(),
        onIncrement = { viewModel.handleIntent(CounterIntent.Increment) },
        onDecrement = { viewModel.handleIntent(CounterIntent.Decrement) }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val viewModel = CounterViewModel()
    CounterApp(viewModel)
}



