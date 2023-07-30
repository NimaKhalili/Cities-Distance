package com.example.citiesdistance.data.repo.source

import com.example.citiesdistance.data.Distance
import com.example.citiesdistance.data.DistanceItemCount
import com.example.citiesdistance.data.MessageResponse
import com.google.gson.JsonElement
import io.reactivex.Single

interface DistanceDataSource {

    suspend fun getDistance(mabda: String, maghsad: String): JsonElement
    suspend fun sendDistance(beginning: String, destination: String, distance: JsonElement): JsonElement
    fun getDistanceList(): Single<List<Distance>>
    suspend fun getDistanceCount(): DistanceItemCount
    fun deleteDistance(id: Int): Single<MessageResponse>
}