package com.example.citiesdistance.data.repo.source

import com.example.citiesdistance.services.http.ApiService
import com.google.gson.JsonElement
import io.reactivex.Single

class DistanceRemoteDataSource(val apiService: ApiService) : DistanceDataSource {
    override fun getDistance(mabda: String, maghsad: String): Single<JsonElement> =
        apiService.getDistance(mabda, maghsad)

    override fun saveDistance(beginning: String, destination: String, distance: JsonElement) =
        apiService.sendDistance(beginning, destination, distance.asInt)
}