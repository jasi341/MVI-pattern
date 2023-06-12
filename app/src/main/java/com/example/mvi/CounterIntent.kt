package com.example.mvi

sealed class TodoIntent{
object LoadTodosIntent:TodoIntent()
}

