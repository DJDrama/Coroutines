package com.dj.coroutines_practice

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TestListener {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun onFinish(onFinished: (Int)->Unit){
        coroutineScope.launch {
            delay(2000)

            onFinished(5)
        }
    }
}