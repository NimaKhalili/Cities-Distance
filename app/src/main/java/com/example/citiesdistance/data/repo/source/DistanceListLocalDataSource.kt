package com.example.citiesdistance.data.repo.source

import com.example.citiesdistance.data.Distance
import com.example.citiesdistance.data.DistanceListCount
import com.example.citiesdistance.data.MessageResponse
import io.reactivex.Single

class DistanceListLocalDataSource : DistanceListDataSource {

    override fun getDistanceList(): Single<List<Distance>> {
        TODO("Not yet implemented")
    }

    override fun getDistanceCount(): Single<DistanceListCount> {
        TODO("Not yet implemented")
    }

    override fun deleteDistance(id: Int): Single<MessageResponse> {
        TODO("Not yet implemented")
    }
}