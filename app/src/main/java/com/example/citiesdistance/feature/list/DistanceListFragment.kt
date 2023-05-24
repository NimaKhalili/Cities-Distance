package com.example.citiesdistance.feature.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.citiesdistance.common.BaseFragment
import com.example.citiesdistance.data.Distance
import com.example.citiesdistance.databinding.FragmentDistanceListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DistanceListFragment : BaseFragment() {
    private var _binding: FragmentDistanceListBinding? = null
    private val binding get() = _binding!!
    private val distanceListViewModel : DistanceListViewModel by viewModel()
    private val distanceListAdapter = DistanceListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDistanceListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewDistanceList.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.recyclerViewDistanceList.adapter = distanceListAdapter
        prepareDistanceListViewModel()
    }

    private fun prepareDistanceListViewModel(){
        distanceListViewModel.distanceListLiveData.observe(viewLifecycleOwner){
            it?.let {
                distanceListAdapter.distanceList = it as ArrayList<Distance>
            }
        }

        distanceListViewModel.progressDialogLiveData.observe(viewLifecycleOwner){
            setProgressIndicator(it)
        }
    }

    override fun onStart() {
        super.onStart()
        distanceListViewModel.refresh()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}