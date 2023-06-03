package com.example.citiesdistance.data.repo.source

import com.example.citiesdistance.data.Distance
import com.example.citiesdistance.data.DistanceListCount
import com.example.citiesdistance.services.http.ApiService
import io.reactivex.Single

class DistanceListRemoteDataSource(val apiService: ApiService) : DistanceListDataSource {

    override fun getDistanceList(): Single<List<Distance>> = apiService.getDistanceList()

    override fun getDistanceCount(): Single<DistanceListCount> = apiService.getDistanceCount()
}