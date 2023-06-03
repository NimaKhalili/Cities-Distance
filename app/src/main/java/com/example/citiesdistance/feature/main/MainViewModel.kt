package com.example.citiesdistance.feature.main

import com.example.citiesdistance.common.BaseSingleObserver
import com.example.citiesdistance.common.BaseViewModel
import com.example.citiesdistance.data.DistanceListCount
import com.example.citiesdistance.data.repo.DistanceListRepository
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus

class MainViewModel(private val distanceListRepository: DistanceListRepository): BaseViewModel() {

    fun getDistanceListCount(){
        distanceListRepository.getDistanceCount()
            .subscribeOn(Schedulers.io())
            .subscribe(object :BaseSingleObserver<DistanceListCount>(compositeDisposable){
                override fun onSuccess(t: DistanceListCount) {
                    EventBus.getDefault().postSticky(t)
                }
            })

    }
}