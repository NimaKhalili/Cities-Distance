package com.example.citiesdistance.data.repo.source

import com.example.citiesdistance.data.Distance
import io.reactivex.Single

class DistanceListLocalDataSource : DistanceListDataSource {
    override fun getDistanceList(): Single<List<Distance>> {
        TODO("Not yet implemented")
    }
}