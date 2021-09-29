package com.dj.coroutines_practice

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
            /** launch는 해당 coroutine의 job을 리턴하므로 job 변수에 assign 해준다 */
            job = coroutineScopeMainImmediate.launch {
                var i=0
                while(i<5) {
                    tvMessage.text = "Started"
                    button.isEnabled = false
                    runTime()
                    /** 끝나면 Toast 를 띄우기! */
                    Toast.makeText(requireContext(), "Ended!", Toast.LENGTH_LONG).show()
                    tvMessage.text = "Ended"
                    button.isEnabled = true
                }
            }
        }
    }

    private suspend fun runTime() {
        var seconds = 5
        while (seconds >= 0) {
            Log.e(TAG, "Remaining Time: $seconds")
            tvTime.text = "${seconds--}"
            delay(1000)
        }
    }

    override fun onStop() {
        super.onStop()
        /** 홈화면으로 화면전환이 이루어질 시 해당 job을 취소한다 */
        job?.cancel()
    }
}