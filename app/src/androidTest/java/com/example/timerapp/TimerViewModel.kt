package com.example.timerapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TimerViewModel : ViewModel() {
    private val _timeLeft = MutableStateFlow(60) // Set timer awal 60 detik
    val timeLeft: StateFlow<Int> = _timeLeft

    private var timerJob: kotlinx.coroutines.Job? = null

    fun startTimer() {
        if (timerJob?.isActive == true) return // Hindari timer berjalan dua kali

        timerJob = viewModelScope.launch {
            while (_timeLeft.value > 0) {
                delay(1000)
                _timeLeft.value -= 1
            }
        }
    }

    fun pauseTimer() {
        timerJob?.cancel()
    }

    fun resetTimer() {
        timerJob?.cancel()
        _timeLeft.value = 60
    }
}
