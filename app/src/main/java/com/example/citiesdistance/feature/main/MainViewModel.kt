package com.example.citiesdistance.feature.main

import com.example.citiesdistance.common.BaseSingleObserver
import com.example.citiesdistance.common.BaseViewModel
import com.example.citiesdistance.data.DistanceItemCount
import com.example.citiesdistance.data.repo.DistanceListRepository
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus

class MainViewModel(private val distanceListRepository: DistanceListRepository): BaseViewModel() {

    fun getDistanceItemCount(){
        distanceListRepository.getDistanceCount()
            .subscribeOn(Schedulers.io())
            .subscribe(object :BaseSingleObserver<DistanceItemCount>(compositeDisposable){
                override fun onSuccess(t: DistanceItemCount) {
                    EventBus.getDefault().postSticky(t)
                }
            })

    }
}