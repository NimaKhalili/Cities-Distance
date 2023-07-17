package com.example.citiesdistance.data.repo

import com.example.citiesdistance.data.Distance
import com.example.citiesdistance.data.DistanceItemCount
import com.example.citiesdistance.data.MessageResponse
import com.google.gson.JsonElement
import io.reactivex.Single

interface DistanceRepository {

    fun getDistance(mabda: String, maghsad: String): Single<JsonElement>

    fun sendDistance(
        beginning: String,
        destination: String,
        distance: JsonElement
    ): Single<JsonElement>

    fun getDistanceList(): Single<List<Distance>>
    suspend fun getDistanceCount(): DistanceItemCount
    fun deleteDistance(id: Int): Single<MessageResponse>
}