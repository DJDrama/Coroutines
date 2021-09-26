package com.dj.coroutines_practice

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.coroutines.*

class BasicCoroutineFragment : Fragment(R.layout.fragment_coroutine_basic) {
    private val TAG = this::class.simpleName
    private val coroutineScopeMainImmediate = CoroutineScope(Dispatchers.Main.immediate)

    /** Job 타입 변수 추가 */
    private var job: Job? = null

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
            job = coroutineScopeMainImmediate.launch {
                try {
                    tvMessage.text = "Started"
                    button.isEnabled = false
                    runTime()
                    /** 끝나면 Toast 를 띄우기! */
                    Toast.makeText(requireContext(), "Ended!", Toast.LENGTH_LONG).show()
                    tvMessage.text = "Ended"
                    button.isEnabled = true
                } catch (e: CancellationException) {
                    e.printStackTrace()
                    tvMessage.text = "Cancelled"
                } finally {
                    button.isEnabled = true
                    tvTime.text = ""
                }
            }
        }
    }

    private suspend fun runTime() {
        var seconds = 5
        while (seconds >= 0) {
            Log.d(TAG, "Remaining Seconds: $seconds")
            tvTime.text = "${seconds--}"
            delay(1000)
        }
    }

    override fun onStop() {
        super.onStop()
        job?.cancel()
    }
}