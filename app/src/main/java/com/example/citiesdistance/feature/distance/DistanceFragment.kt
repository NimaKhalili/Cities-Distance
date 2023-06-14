package com.example.citiesdistance.feature.distance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.citiesdistance.R
import com.example.citiesdistance.common.BaseFragment
import com.example.citiesdistance.data.Distance
import com.example.citiesdistance.databinding.FragmentDistanceBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DistanceFragment : BaseFragment() {
    private var _binding: FragmentDistanceBinding? = null
    private val binding get() = _binding!!
    private val distanceViewModel: DistanceViewModel by viewModel()
    private var adapter: DistanceAdapter = DistanceAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDistanceBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareSwipeRefreshLayout()
        prepareRecyclerViewItemsListener()
        prepareSwipeRefreshLayoutListener()
        prepareDistanceListViewModel()
    }

    private fun prepareDistanceListViewModel() {
        distanceViewModel.distanceListLiveData.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it as ArrayList<Distance>)
                adapter.submitList(it)
                binding.recyclerViewDistance.adapter = adapter
            }
        }

        distanceViewModel.progressDialogLiveData.observe(viewLifecycleOwner) {
            setProgressIndicator(it)
        }

        distanceViewModel.snackBarLiveData.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { message ->
                showSnackBar(message)
            }
        }
    }

    private fun prepareSwipeRefreshLayoutListener() {
        binding.swipeRefreshLayoutDistance.setOnRefreshListener {
            distanceViewModel.refresh()
            binding.swipeRefreshLayoutDistance.isRefreshing = false
        }
    }

    private fun prepareRecyclerViewItemsListener() {
        adapter.onLongClick = {
            distanceViewModel.deleteDistance(it.id)
        }
    }

    private fun prepareSwipeRefreshLayout() {
        binding.swipeRefreshLayoutDistance.setColorSchemeResources(R.color.colorPrimary)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}