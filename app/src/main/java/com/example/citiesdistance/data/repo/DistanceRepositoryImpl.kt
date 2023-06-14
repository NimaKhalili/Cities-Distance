package com.example.citiesdistance.data.repo

import com.example.citiesdistance.data.Distance
import com.example.citiesdistance.data.DistanceItemCount
import com.example.citiesdistance.data.MessageResponse
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

    override fun sendDistance(beginning: String, destination: String, distance: JsonElement) =
        remoteDataSource.sendDistance(beginning, destination, distance)

    override fun getDistanceList(): Single<List<Distance>> = remoteDataSource.getDistanceList()

    override fun getDistanceCount(): Single<DistanceItemCount> = remoteDataSource.getDistanceCount()

    override fun deleteDistance(id: Int): Single<MessageResponse> = remoteDataSource.deleteDistance(id)
}