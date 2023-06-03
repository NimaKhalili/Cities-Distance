package com.example.citiesdistance.data.repo.source

import com.example.citiesdistance.data.Distance
import com.example.citiesdistance.data.DistanceListCount
import io.reactivex.Single

interface DistanceListDataSource {

    fun getDistanceList(): Single<List<Distance>>
    fun getDistanceCount(): Single<DistanceListCount>
}