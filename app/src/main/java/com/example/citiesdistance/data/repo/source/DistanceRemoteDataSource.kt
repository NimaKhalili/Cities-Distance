package com.example.citiesdistance.data.repo.source

import com.example.citiesdistance.data.Distance
import com.example.citiesdistance.data.DistanceItemCount
import com.example.citiesdistance.data.MessageResponse
import com.example.citiesdistance.services.http.ApiService
import com.google.gson.JsonElement

class DistanceRemoteDataSource(val apiService: ApiService) : DistanceDataSource {
    override suspend fun getDistance(mabda: String, maghsad: String): JsonElement =
        apiService.getDistance(mabda, maghsad)

    override suspend fun sendDistance(beginning: String, destination: String, distance: JsonElement) =
        apiService.sendDistance(beginning, destination, distance.asInt)

    override suspend fun getDistanceList(): List<Distance> = apiService.getDistanceList()

    override suspend fun getDistanceCount(): DistanceItemCount = apiService.getDistanceCount()

    override suspend fun deleteDistance(id: Int): MessageResponse = apiService.deleteDistance(id)
}