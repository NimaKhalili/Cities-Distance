package com.example.citiesdistance.feature.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.citiesdistance.common.BaseSingleObserver
import com.example.citiesdistance.common.BaseViewModel
import com.example.citiesdistance.common.asyncNetworkRequest
import com.example.citiesdistance.data.Distance
import com.example.citiesdistance.data.repo.DistanceListRepository

class DistanceListViewModel(private val distanceListRepository: DistanceListRepository) :
    BaseViewModel() {
    private val _distanceListLiveData = MutableLiveData<List<Distance>>()
    val distanceListLiveData : LiveData<List<Distance>>
        get() = _distanceListLiveData

    init {
        progressDialogLiveData.value = true
        distanceListRepository.getDistanceList()
            .asyncNetworkRequest()
            .doFinally { progressDialogLiveData.value = false }
            .subscribe(object : BaseSingleObserver<List<Distance>>(compositeDisposable){
                override fun onSuccess(t: List<Distance>) {
                    _distanceListLiveData.value = t
                }
            })
    }
}