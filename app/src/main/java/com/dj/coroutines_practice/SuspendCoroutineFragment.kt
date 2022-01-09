package com.dj.coroutines_practice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dj.coroutines_practice.databinding.FragmentSuspendCoroutineBinding

class SuspendCoroutineFragment : Fragment() {

    private var _binding: FragmentSuspendCoroutineBinding? = null
    private val binding: FragmentSuspendCoroutineBinding
        get() = _binding!!

    private val viewModel: SuspendCoroutineViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSuspendCoroutineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObservers()
    }

    private fun subscribeToObservers() {
        viewModel.finishedValue.observe(viewLifecycleOwner) {
            it?.let {
                binding.textView.text = "Finished Value: $it"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}