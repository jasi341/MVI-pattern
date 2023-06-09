package com.example.mvi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel:ViewModel() {
    private val _model = MutableLiveData(CounterModel())

    val model :LiveData<CounterModel> = _model

    fun handleIntent(intent: CounterIntent){
        val newModel = counterReducer(_model.value!!,intent)
        _model.value = newModel
    }
}