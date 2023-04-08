package com.example.citiesdistance.feature.main

import androidx.lifecycle.MutableLiveData
import com.example.citiesdistance.common.BaseViewModel
import com.example.citiesdistance.data.repo.DistanceRepository
import com.google.gson.JsonElement
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainViewModel(distanceRepository: DistanceRepository) : BaseViewModel() {
    val distanceLiveData = MutableLiveData<JsonElement>()

    init {
        distanceRepository.getDistance("قزوین", "تهران")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<JsonElement> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    Timber.e(e)
                }

                override fun onSuccess(t: JsonElement) {
                    distanceLiveData.value = t
                }

            })
    }
}