package com.example.citiesdistance.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.citiesdistance.common.BaseSingleObserver
import com.example.citiesdistance.common.BaseViewModel
import com.example.citiesdistance.common.Event
import com.example.citiesdistance.common.asyncNetworkRequest
import com.example.citiesdistance.data.DistanceListCount
import com.example.citiesdistance.data.repo.DistanceRepository
import com.google.gson.JsonElement
import org.greenrobot.eventbus.EventBus

class HomeViewModel(private val distanceRepository: DistanceRepository) : BaseViewModel() {
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
                    refreshBadgeCount()
                }
            })
    }

    private fun refreshBadgeCount() {
        val distanceListCount =
            EventBus.getDefault().getStickyEvent(DistanceListCount::class.java)
        distanceListCount?.let {
            it.count += 1
            EventBus.getDefault().postSticky(it)
        }
    }

    private fun sendDistanceToServer(beginning: String, destination: String, distance: JsonElement){
        distanceRepository.sendDistance(beginning, destination, distance)
            .asyncNetworkRequest()
            .subscribe(object : BaseSingleObserver<JsonElement>(compositeDisposable){
                override fun onSuccess(response: JsonElement) {
                    if (response.asString == "SUCCESS")
                        snackBarLiveData.value = Event("با موفقیت اضافه شد")
                    else if (response.asString == "FAILED")
                        snackBarLiveData.value = Event("مشکلی در اضافه کردن پیش آمده")
                }
            })
    }
}