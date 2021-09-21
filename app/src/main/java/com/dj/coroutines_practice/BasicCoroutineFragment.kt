package com.dj.coroutines_practice

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BasicCoroutineFragment : Fragment(R.layout.fragment_coroutine_basic) {
    private val TAG = this::class.simpleName
    private val coroutineScopeMain = CoroutineScope(Dispatchers.Main)
    private val coroutineScopeMainImmediate = CoroutineScope(Dispatchers.Main.immediate)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        testDispatchersMain()
        // testDispatchersMainImmediate()
        // testLifecycleScope()
    }

    private fun testDispatchersMain() {
        println("$TAG Zero")
        coroutineScopeMain.launch {
            println("$TAG First")
        }
        println("$TAG Second")
    }

    private fun testDispatchersMainImmediate() {
        println("$TAG Zero")
        coroutineScopeMainImmediate.launch {
            println("$TAG First")
        }
        println("$TAG Second")
    }

    private fun testLifecycleScope() {
        println("$TAG Zero")
        lifecycleScope.launch {
            println("$TAG First")
        }
        println("$TAG Second")
    }
}