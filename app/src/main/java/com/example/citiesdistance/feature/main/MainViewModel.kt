package com.example.citiesdistance.feature.main

import androidx.lifecycle.MutableLiveData
import com.example.citiesdistance.common.BaseSingleObserver
import com.example.citiesdistance.common.BaseViewModel
import com.example.citiesdistance.common.asyncNetworkRequest
import com.example.citiesdistance.data.repo.DistanceRepository
import com.google.gson.JsonElement

class MainViewModel(private val distanceRepository: DistanceRepository) : BaseViewModel() {
    val distanceLiveData = MutableLiveData<JsonElement>()

    fun getDistance(beginning: String, destination: String) {
        distanceRepository.getDistance(beginning, destination)
            .asyncNetworkRequest()
            .subscribe(object : BaseSingleObserver<JsonElement>(compositeDisposable) {
                override fun onSuccess(t: JsonElement) {
                    distanceLiveData.value = t
                }
            })
    }
}