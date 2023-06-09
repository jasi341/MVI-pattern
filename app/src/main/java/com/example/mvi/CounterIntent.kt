package com.example.mvi

sealed class CounterIntent{
    object Increment :CounterIntent()
    object Decrement :CounterIntent()
}

fun counterReducer(model: CounterModel,intent: CounterIntent):CounterModel{
    return when(intent){
        CounterIntent.Increment -> model.copy(count = model.count+1)
        CounterIntent.Decrement -> model.copy(count = model.count-1)
    }
}
