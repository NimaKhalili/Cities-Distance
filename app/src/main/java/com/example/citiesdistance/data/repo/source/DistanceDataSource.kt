package com.example.citiesdistance.data.repo.source

import com.example.citiesdistance.data.Distance
import io.reactivex.Single

interface DistanceDataSource {

    fun distanceCalculate(url:String, mabda: String, maghsad: String): Single<Distance>
}