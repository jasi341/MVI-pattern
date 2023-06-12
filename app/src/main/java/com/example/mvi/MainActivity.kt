package com.example.mvi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvi.ui.theme.MVITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            MVITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TodoApp(TodoViewModel())
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
fun TodoListView(todos:List<Todo>){
    LazyColumn(
        Modifier
            .fillMaxWidth()
            .background(Color.White)){
        items(todos){todos ->
            Card(modifier = Modifier.fillMaxWidth().padding(2.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Black.copy(alpha = 0.7f)
                )
            ) {
                Text(
                    text = "ID: ${todos.id}",
                    fontSize = 16.sp,
                    color = Color.Yellow,
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 10.dp, end = 10.dp)
                )
                Text(
                    text = "Title: ${todos.title}",
                    fontSize = 16.sp,
                    color = Color.Yellow,
                    modifier = Modifier.padding( bottom = 10.dp ,start = 10.dp, end = 10.dp)
                )
                Text(
                    text = " IsCompleted: ${todos.completed}",
                    fontSize = 16.sp,
                    color = Color.Yellow,
                    modifier = Modifier.padding( bottom = 10.dp,start = 10.dp, end = 10.dp)
                )

            }

        }
    }
}



@Composable
fun TodoApp(viewModel: TodoViewModel) {
    val currentState: TodoState by viewModel.state

    LaunchedEffect(true) {
        viewModel.handleIntent(TodoIntent.LoadTodosIntent)
    }

    when (val state = currentState) {
        is TodoState.Loading -> {
            // Show loading state
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                CircularProgressIndicator()
            }

        }
        is TodoState.Loaded -> {
            // Show loaded state with todos
            TodoListView(todos = state.todos)
        }
        is TodoState.Error -> {
            // Show error state
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                CircularProgressIndicator()
                Text(text = "Error: ${state.message}", color = Color.Black)
            }

        }
    }
}


