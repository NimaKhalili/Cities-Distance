package com.example.citiesdistance.feature.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.citiesdistance.common.BaseFragment
import com.example.citiesdistance.data.Distance
import com.example.citiesdistance.databinding.FragmentDistanceListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DistanceListFragment : BaseFragment() {
    private var _binding: FragmentDistanceListBinding? = null
    private val binding get() = _binding!!
    private val distanceListViewModel: DistanceListViewModel by viewModel()
    private var adapter: DistanceListAdapter = DistanceListAdapter()

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

        prepareRecyclerViewItemsListener()
        prepareDistanceListViewModel()
    }

    private fun prepareDistanceListViewModel() {
        distanceListViewModel.distanceListLiveData.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it as ArrayList<Distance>)
                adapter.submitList(it)
                binding.recyclerViewDistanceList.adapter = adapter
            }
        }

        distanceListViewModel.progressDialogLiveData.observe(viewLifecycleOwner) {
            setProgressIndicator(it)
        }

        distanceListViewModel.snackBarLiveData.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { message ->
                showSnackBar(message)
            }
        }
    }

    private fun prepareRecyclerViewItemsListener() {
        adapter.onClick = {
            distanceListViewModel.deleteDistance(it.id)
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