package com.example.citiesdistance.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.citiesdistance.common.BaseSingleObserver
import com.example.citiesdistance.common.BaseViewModel
import com.example.citiesdistance.common.asyncNetworkRequest
import com.example.citiesdistance.data.repo.DistanceRepository
import com.google.gson.JsonElement
import timber.log.Timber

class MainViewModel(private val distanceRepository: DistanceRepository) : BaseViewModel() {
    private val _distanceLiveData = MutableLiveData<JsonElement>()
    val distanceLiveData:LiveData<JsonElement>
        get() = _distanceLiveData

    fun getDistance(beginning: String, destination: String) {
        progressDialogLiveData.value = true
        distanceRepository.getDistance(beginning, destination)
            .asyncNetworkRequest()
            .doFinally { progressDialogLiveData.value = false }
            .subscribe(object : BaseSingleObserver<JsonElement>(compositeDisposable) {
                override fun onSuccess(t: JsonElement) {
                    _distanceLiveData.value = t
                    sendDistanceToServer(beginning, destination, t)
                }
            })
    }

     fun sendDistanceToServer(beginning: String, destination: String, distance: JsonElement){
        distanceRepository.sendDistanceToServer(beginning, destination, distance)
            .asyncNetworkRequest()
            .subscribe(object : BaseSingleObserver<JsonElement>(compositeDisposable){
                override fun onSuccess(response: JsonElement) {
                    if (response.asString == "SUCCESS")
                        Timber.i(response.toString())
                    else if (response.asString == "FAILED")
                        Timber.i(response.toString())
                }
            })
    }
}