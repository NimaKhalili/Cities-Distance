package com.example.citiesdistance.data.repo.source

import com.google.gson.JsonElement
import io.reactivex.Single

interface DistanceDataSource {

    fun getDistance(mabda: String, maghsad: String): Single<JsonElement>

    fun saveDistance(beginning: String, destination: String, distance: JsonElement): Single<JsonElement>
}