package com.example.citiesdistance.data.repo.source

import com.example.citiesdistance.data.Distance
import com.example.citiesdistance.services.http.ApiService
import io.reactivex.Single

class DistanceRemoteDataSource(val apiService: ApiService) : DistanceDataSource {
    override fun distanceCalculate(url: String, mabda: String, maghsad: String): Single<Distance> =
        apiService.distanceCalculate(url, mabda, maghsad)
}