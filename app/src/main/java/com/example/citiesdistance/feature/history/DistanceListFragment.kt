package com.example.citiesdistance.feature.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.citiesdistance.common.BaseFragment
import com.example.citiesdistance.databinding.FragmentDistanceListBinding

class DistanceListFragment : BaseFragment() {
    private var _binding: FragmentDistanceListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDistanceListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}