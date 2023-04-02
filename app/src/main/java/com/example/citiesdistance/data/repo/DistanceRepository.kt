package com.example.citiesdistance.data.repo

import com.example.citiesdistance.data.Distance
import io.reactivex.Single

interface DistanceRepository {

    fun distanceCalculate(url:String, mabda: String, maghsad: String):Single<Distance>
}