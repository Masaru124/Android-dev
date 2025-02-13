package com.example.counter

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.counter.ui.theme.Countermodel


class CounterViewModel(private val repository: Countermodel.counterrepo=Countermodel.counterrepo()) : ViewModel() {
    private val _count = mutableStateOf(repository.getcounter().count)

    val count :MutableState<Int> = _count

    fun incr(){
        repository.incr()
        _count.value = repository.getcounter().count
    }
    fun decrement() {
        if (_count.value!=0) {
            repository.decr()
            _count.value = repository.getcounter().count

        }
    }
    fun reset(){
        _count.value=0
    }

}