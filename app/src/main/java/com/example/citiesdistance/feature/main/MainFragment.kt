package com.example.citiesdistance.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import com.example.citiesdistance.R
import com.example.citiesdistance.common.BaseFragment
import com.example.citiesdistance.databinding.FragmentMainBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment() {
    private val mainViewModel: MainViewModel by viewModel()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private var citiesArrayList: ArrayList<String>? = null

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
        citiesArrayList = ArrayList<String>(listOf(*resources.getStringArray(R.array.citiesList)))

        prepareMainViewModel()
        prepareTextInputLayoutMainBeginning()
        prepareTextInputLayoutMainDestination()
        prepareButtonMainDistanceCalculateOnClick()
    }

    private fun prepareButtonMainDistanceCalculateOnClick() {
        binding.buttonMainDistanceCalculate.setOnClickListener {
            val beginning = binding.textInputEditTextMainBeginning.text.toString()
            val destination = binding.textInputEditTextMainDestination.text.toString()
            if (beginning.isNotEmpty() && destination.isNotEmpty()) {
                getDistanceCalculate(beginning, destination)
            }else{
                Toast.makeText(context, "لطفا هر دو گزینه را پر کنید", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getDistanceCalculate(beginning: String, destination: String) {
        if (beginning != destination)
            mainViewModel.getDistance(beginning, destination)
        else
            Toast.makeText(context, "امکان محاسبه دو شهر مشابه وجود ندارد", Toast.LENGTH_SHORT).show()
    }

    private fun prepareTextInputLayoutMainDestination() {
        binding.textInputLayoutMainDestination.setStartIconOnClickListener {
            preparePopupMenu(
                binding.textInputLayoutMainDestination,
                binding.textInputEditTextMainDestination
            )
        }
    }

    private fun prepareTextInputLayoutMainBeginning() {
        binding.textInputLayoutMainBeginning.setStartIconOnClickListener {
            preparePopupMenu(
                binding.textInputLayoutMainBeginning,
                binding.textInputEditTextMainBeginning
            )
        }
    }

    private fun preparePopupMenu(
        textInputLayout: TextInputLayout,
        textInputEditText: TextInputEditText
    ) {
        val popupMenu = PopupMenu(context, textInputLayout)
        citiesArrayList?.forEach { city -> popupMenu.menu.add(Menu.NONE, 1, Menu.NONE, city) }
        popupMenu.setOnMenuItemClickListener { item ->
            textInputEditText.setText(item.toString())
            true
        }
        popupMenu.show()
    }

    private fun prepareMainViewModel() {
        mainViewModel.distanceLiveData.observe(viewLifecycleOwner) {
            binding.textViewMainDistanceShow.text = "$it کیلومتر"
        }

        mainViewModel.progressDialogLiveData.observe(viewLifecycleOwner) {
            if (it)
                binding.progressBarMainDistanceShow.visibility = View.VISIBLE
            else
                binding.progressBarMainDistanceShow.visibility = View.INVISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}