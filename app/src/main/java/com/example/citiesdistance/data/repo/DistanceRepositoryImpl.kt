package com.example.citiesdistance.data.repo

import com.example.citiesdistance.data.repo.source.DistanceDataSource
import com.example.citiesdistance.data.repo.source.DistanceLocalDataSource
import com.google.gson.JsonElement
import io.reactivex.Single

class DistanceRepositoryImpl(
    val remoteDataSource: DistanceDataSource,
    val localDataSource: DistanceLocalDataSource
) : DistanceRepository {
    override fun getDistance(mabda: String, maghsad: String): Single<JsonElement> =
        remoteDataSource.getDistance(mabda, maghsad)

    override fun sendDistanceToServer(beginning: String, destination: String, distance: JsonElement) =
        remoteDataSource.saveDistance(beginning, destination, distance)
}