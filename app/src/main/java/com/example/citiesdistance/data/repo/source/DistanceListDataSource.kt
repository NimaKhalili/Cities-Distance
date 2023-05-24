package com.example.citiesdistance.data.repo.source

import com.example.citiesdistance.data.Distance
import io.reactivex.Single

interface DistanceListDataSource {

    fun getDistanceList(): Single<List<Distance>>
}