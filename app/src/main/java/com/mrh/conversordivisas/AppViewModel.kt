package com.mrh.conversordivisas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AppViewModel : ViewModel() {
    private val _history = MutableStateFlow<List<Cambio>>(emptyList())
    val history: StateFlow<List<Cambio>> get() = _history

    fun addCambio(cambio: Cambio) {
        _history.value = _history.value + cambio
    }
}