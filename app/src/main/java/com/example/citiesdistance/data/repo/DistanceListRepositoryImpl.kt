package com.example.citiesdistance.data.repo

import com.example.citiesdistance.data.Distance
import com.example.citiesdistance.data.repo.source.DistanceListDataSource
import com.example.citiesdistance.data.repo.source.DistanceListLocalDataSource
import io.reactivex.Single

class DistanceListRepositoryImpl(
    val remoteDataSource: DistanceListDataSource,
    val localDataSource: DistanceListLocalDataSource
) : DistanceListRepository {
    override fun getDistanceList(): Single<List<Distance>> = remoteDataSource.getDistanceList()
}