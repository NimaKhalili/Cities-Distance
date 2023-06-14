package com.example.citiesdistance.data.repo.source

import com.example.citiesdistance.data.Distance
import com.example.citiesdistance.data.DistanceItemCount
import com.example.citiesdistance.data.MessageResponse
import io.reactivex.Single

interface DistanceListDataSource {

    fun getDistanceList(): Single<List<Distance>>
    fun getDistanceCount(): Single<DistanceItemCount>
    fun deleteDistance(id: Int): Single<MessageResponse>
}