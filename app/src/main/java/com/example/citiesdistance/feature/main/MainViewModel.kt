package com.example.citiesdistance.feature.main

import androidx.lifecycle.viewModelScope
import com.example.citiesdistance.common.BaseViewModel
import com.example.citiesdistance.data.repo.DistanceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus

class MainViewModel(private val distanceRepository: DistanceRepository): BaseViewModel() {

    fun getDistanceItemCount(){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val result = distanceRepository.getDistanceCount()
            EventBus.getDefault().postSticky(result)
        }
    }
}