package com.example.citiesdistance.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.citiesdistance.common.BaseFragment
import com.example.citiesdistance.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainFragment : BaseFragment() {
    private val mainViewModel: MainViewModel by viewModel()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareMainViewModel()
        prepareButtonMainDistanceCalculateOnClick()
    }

    private fun prepareButtonMainDistanceCalculateOnClick() {
        binding.buttonMainDistanceCalculate.setOnClickListener {
            val beginning = binding.textInputEditTextMainBeginning.text.toString()
            val destination = binding.textInputEditTextMainDestination.text.toString()
            mainViewModel.getDistance(beginning, destination)
        }
    }

    private fun prepareMainViewModel() {
        mainViewModel.distanceLiveData.observe(viewLifecycleOwner) {
            Timber.i("Distance Calculate : $it")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}