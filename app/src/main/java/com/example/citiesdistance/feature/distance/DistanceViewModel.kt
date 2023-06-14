package com.example.citiesdistance.feature.distance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.citiesdistance.common.BaseSingleObserver
import com.example.citiesdistance.common.BaseViewModel
import com.example.citiesdistance.common.Event
import com.example.citiesdistance.common.asyncNetworkRequest
import com.example.citiesdistance.data.Distance
import com.example.citiesdistance.data.DistanceItemCount
import com.example.citiesdistance.data.MessageResponse
import com.example.citiesdistance.data.repo.DistanceRepository
import org.greenrobot.eventbus.EventBus

class DistanceViewModel(private val distanceRepository: DistanceRepository) :
    BaseViewModel() {
    private val _distanceLiveData = MutableLiveData<List<Distance>>()
    val distanceLiveData: LiveData<List<Distance>>
        get() = _distanceLiveData

    init {
        prepareDistanceList()
    }

    fun refresh() {
        prepareDistanceList()
    }

    private fun prepareDistanceList() {
        progressDialogLiveData.value = true
        distanceRepository.getDistanceList()
            .asyncNetworkRequest()
            .doFinally { progressDialogLiveData.value = false }
            .subscribe(object : BaseSingleObserver<List<Distance>>(compositeDisposable) {
                override fun onSuccess(t: List<Distance>) {
                    _distanceLiveData.value = t
                }
            })
    }

    fun deleteDistance(distanceId: Int) {
        progressDialogLiveData.value = true
        distanceRepository.deleteDistance(distanceId)
            .asyncNetworkRequest()
            .doFinally { progressDialogLiveData.value = false }
            .subscribe(object : BaseSingleObserver<MessageResponse>(compositeDisposable) {
                override fun onSuccess(t: MessageResponse) {
                    if (t.response == "SUCCESS") {
                        snackBarLiveData.value = Event("با موفقیت حذف شد")
                        refresh()
                        refreshBadgeCount()
                    } else if (t.response == "FAILED") {
                        snackBarLiveData.value = Event("حذف ناموفق بود")
                    }
                }
            })
    }

    private fun refreshBadgeCount() {
        val distanceItemCount =
            EventBus.getDefault().getStickyEvent(DistanceItemCount::class.java)
        distanceItemCount?.let {
            it.count -= 1
            EventBus.getDefault().postSticky(it)
        }
    }
}