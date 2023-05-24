package com.example.citiesdistance.data.repo

import com.example.citiesdistance.data.Distance
import io.reactivex.Single

interface DistanceListRepository {

    fun getDistanceList(): Single<List<Distance>>
}