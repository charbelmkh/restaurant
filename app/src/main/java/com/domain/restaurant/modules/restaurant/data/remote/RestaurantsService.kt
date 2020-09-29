package com.domain.restaurant.modules.restaurant.data.remote

import com.domain.restaurant.modules.restaurant.data.models.Json4KotlinBase
import com.domain.restaurant.modules.restaurant.data.models.Restaurant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestaurantsService {

    @GET("/api/v2.1/search")
    suspend fun fetchAllRestaurants(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("q") query: String,
        @Query("count") count: Int
    ): Response<Json4KotlinBase>



}