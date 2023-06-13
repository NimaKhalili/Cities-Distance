package com.example.citiesdistance.data.repo

import com.example.citiesdistance.data.Distance
import com.example.citiesdistance.data.DistanceListCount
import com.example.citiesdistance.data.MessageResponse
import io.reactivex.Single

interface DistanceListRepository {

    fun getDistanceList(): Single<List<Distance>>
    fun getDistanceCount(): Single<DistanceListCount>
    fun deleteDistance(id: Int): Single<MessageResponse>
}