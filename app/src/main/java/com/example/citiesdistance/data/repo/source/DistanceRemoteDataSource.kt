package com.example.citiesdistance.data.repo.source

import com.example.citiesdistance.data.Distance
import com.example.citiesdistance.data.DistanceItemCount
import com.example.citiesdistance.data.MessageResponse
import com.example.citiesdistance.services.http.ApiService
import com.google.gson.JsonElement
import io.reactivex.Single

class DistanceRemoteDataSource(val apiService: ApiService) : DistanceDataSource {
    override fun getDistance(mabda: String, maghsad: String): Single<JsonElement> =
        apiService.getDistance(mabda, maghsad)

    override fun sendDistance(beginning: String, destination: String, distance: JsonElement) =
        apiService.sendDistance(beginning, destination, distance.asInt)

    override fun getDistanceList(): Single<List<Distance>> = apiService.getDistanceList()

    override fun getDistanceCount(): Single<DistanceItemCount> = apiService.getDistanceCount()

    override fun deleteDistance(id: Int): Single<MessageResponse> = apiService.deleteDistance(id)
}