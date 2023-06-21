package com.example.citiesdistance.feature.distance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.citiesdistance.R
import com.example.citiesdistance.common.BaseSingleObserver
import com.example.citiesdistance.common.BaseViewModel
import com.example.citiesdistance.common.Event
import com.example.citiesdistance.common.asyncNetworkRequest
import com.example.citiesdistance.data.Distance
import com.example.citiesdistance.data.DistanceItemCount
import com.example.citiesdistance.data.EmptyState
import com.example.citiesdistance.data.MessageResponse
import com.example.citiesdistance.data.repo.DistanceRepository
import org.greenrobot.eventbus.EventBus

class DistanceViewModel(private val distanceRepository: DistanceRepository) :
    BaseViewModel() {
    private val _distanceLiveData = MutableLiveData<List<Distance>>()
    val distanceLiveData: LiveData<List<Distance>>
        get() = _distanceLiveData
    private val _emptyStateLiveData = MutableLiveData<EmptyState>()
    val emptyStateLiveData : LiveData<EmptyState>
        get() = _emptyStateLiveData

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
                    prepareList(t)
                }
            })
    }

    private fun prepareList(t: List<Distance>) {
        if (t.isNotEmpty()) {
            _distanceLiveData.value = t
            _emptyStateLiveData.value = EmptyState(false)
        } else {
            _distanceLiveData.value = t
            _emptyStateLiveData.value =
                EmptyState(true, R.string.distance_default_empty_state, true)
        }
    }

    fun deleteDistance(distance: Distance) {
        progressDialogLiveData.value = true
        distanceRepository.deleteDistance(distance.id)
            .asyncNetworkRequest()
            .doFinally { progressDialogLiveData.value = false }
            .subscribe(object : BaseSingleObserver<MessageResponse>(compositeDisposable) {
                override fun onSuccess(t: MessageResponse) {
                    prepareResponse(t, distance)
                }
            })
    }

    private fun prepareResponse(t: MessageResponse, distance: Distance) {
        if (t.response == "SUCCESS") {
            refreshLiveData(distance)
            refreshBadgeCount()
            checkListToShowEmptyState()
            snackBarLiveData.value = Event("با موفقیت حذف شد")
        } else if (t.response == "FAILED") {
            snackBarLiveData.value = Event("حذف ناموفق بود")
        }
    }

    private fun checkListToShowEmptyState() {
        _distanceLiveData.value?.let {
            if (it.isEmpty())
                _emptyStateLiveData.value = EmptyState(true, R.string.distance_finished_empty_state)
        }
    }

    private fun refreshBadgeCount() {
        val distanceItemCount =
            EventBus.getDefault().getStickyEvent(DistanceItemCount::class.java)
        distanceItemCount?.let {
            it.count -= 1
            EventBus.getDefault().postSticky(it)
        }
    }

    private fun refreshLiveData(distance: Distance) {
        _distanceLiveData.value = _distanceLiveData.value?.toMutableList()?.apply {
            remove(distance)
        }
    }
}