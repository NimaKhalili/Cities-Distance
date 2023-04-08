package com.example.citiesdistance.data.repo.source

import com.google.gson.JsonElement
import io.reactivex.Single

class DistanceLocalDataSource:DistanceDataSource {
    override fun getDistance(mabda: String, maghsad: String): Single<JsonElement> {
        TODO("Not yet implemented")
    }
}