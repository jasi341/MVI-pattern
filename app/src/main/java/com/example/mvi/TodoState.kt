package com.example.mvi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.lang.Exception

sealed class TodoState{
    object Loading :TodoState()
    data class Loaded(val todos:List<Todo>):TodoState()
    data class Error(val message:String):TodoState()
}

 suspend fun todoReducer(state: TodoState, intent: TodoIntent):TodoState{
    return when(intent){
        is TodoIntent.LoadTodosIntent ->{
            try {
                val todos = fetchTodosFromApi()
                TodoState.Loaded(todos)
            }catch (e:Exception){
                TodoState.Error(e.message?:"Error fetching data")
            }
        }
    }
}

private val retrofit = Retrofit.Builder()
    .baseUrl("https://jsonplaceholder.typicode.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
private val todoApiService = retrofit.create(TodoApiService::class.java)

suspend fun fetchTodosFromApi():List<Todo>{

    return  try{
        todoApiService.getTodos()
    }catch (e:Exception){
        emptyList()
    }

}
