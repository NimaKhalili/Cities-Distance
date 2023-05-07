package com.example.citiesdistance.data.repo

import com.google.gson.JsonElement
import io.reactivex.Single

interface DistanceRepository {

    fun getDistance(mabda: String, maghsad: String): Single<JsonElement>

    fun sendDistanceToServer(
        beginning: String,
        destination: String,
        distance: JsonElement
    ): Single<JsonElement>
}