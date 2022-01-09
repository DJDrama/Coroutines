package com.dj.coroutines_practice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class SuspendCoroutineViewModel : ViewModel() {

    private var testListener: TestListener = TestListener()
    private val _finishedValue = MutableLiveData<Int>()
    val finishedValue: LiveData<Int>
        get() = _finishedValue

    init {
        getFinishedValue()
    }

    private fun getFinishedValue() {
        viewModelScope.launch {
            var finishedValue = 0
            finishedValue = suspendCoroutine<Int> { continuation->
                testListener.onFinish {
                    continuation.resume(it)
                }
            }
            _finishedValue.value = finishedValue
        }
    }
}
