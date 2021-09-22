package com.dj.coroutines_practice

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BasicCoroutineFragment : Fragment(R.layout.fragment_coroutine_basic) {
    private val TAG = this::class.simpleName
    private val coroutineScopeMainImmediate = CoroutineScope(Dispatchers.Main.immediate)

    private lateinit var button: Button
    private lateinit var tvTime: TextView
    private lateinit var tvMessage: TextView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        tvTime = view.findViewById(R.id.tv_time)
        tvMessage = view.findViewById(R.id.tv_message)

        button = view.findViewById(R.id.button)
        button.setOnClickListener {
            coroutineScopeMainImmediate.launch {
                tvMessage.text = "Started"
                button.isEnabled = false
                runTime()
                tvMessage.text = "Ended"
                button.isEnabled = true
            }
        }
    }

    private suspend fun runTime() {
        var seconds = 5
        while (seconds >= 0) {
            tvTime.text = "${seconds--}"
            delay(1000)
        }
    }
}