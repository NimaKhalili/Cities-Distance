package com.example.citiesdistance.services.http

import com.example.citiesdistance.data.Distance
import com.example.citiesdistance.data.DistanceListCount
import com.google.gson.JsonElement
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("distance/index.php")
    fun getDistance(
        @Query("mabda") mabda: String,
        @Query("maghsad") maghsad: String
    ): Single<JsonElement>

    @POST("https://myhostforever.ir/CitiesDistance/Distance/saveDistance.php")
    fun sendDistance(
        @Query("beginning") beginning: String,
        @Query("destination") destination: String,
        @Query("distance") distance: Int
    ): Single<JsonElement>

    @GET("http://myhostforever.ir/CitiesDistance/Distance/getDistanceList.php")
    fun getDistanceList(): Single<List<Distance>>

    @GET("https://myhostforever.ir/CitiesDistance/Distance/getDistanceListCount.php/")
    fun getDistanceCount(): Single<DistanceListCount>
}

fun createApiServiceInstance(): ApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.codebazan.ir/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(ApiService::class.java)
}