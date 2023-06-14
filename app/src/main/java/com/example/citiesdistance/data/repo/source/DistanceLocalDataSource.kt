package com.example.citiesdistance.data.repo.source

import com.example.citiesdistance.data.Distance
import com.example.citiesdistance.data.DistanceItemCount
import com.example.citiesdistance.data.MessageResponse
import com.google.gson.JsonElement
import io.reactivex.Single

class DistanceLocalDataSource : DistanceDataSource {
    override fun getDistance(mabda: String, maghsad: String): Single<JsonElement> {
        TODO("Not yet implemented")
    }

    override fun sendDistance(beginning: String, destination: String, distance: JsonElement): Single<JsonElement> {
        TODO("Not yet implemented")
    }

    override fun getDistanceList(): Single<List<Distance>> {
        TODO("Not yet implemented")
    }

    override fun getDistanceCount(): Single<DistanceItemCount> {
        TODO("Not yet implemented")
    }

    override fun deleteDistance(id: Int): Single<MessageResponse> {
        TODO("Not yet implemented")
    }
}