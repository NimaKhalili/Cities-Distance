package com.example.citiesdistance.data.repo

import com.example.citiesdistance.data.Distance
import com.example.citiesdistance.data.repo.source.DistanceDataSource
import com.example.citiesdistance.data.repo.source.DistanceLocalDataSource
import io.reactivex.Single

class DistanceRepositoryImpl(
    val remoteDataSource: DistanceDataSource,
    val localDataSource: DistanceLocalDataSource
) : DistanceRepository {
    override fun distanceCalculate(url: String, mabda: String, maghsad: String): Single<Distance> =
        remoteDataSource.distanceCalculate(url, mabda, maghsad)
}