package com.domain.restaurant.modules.restaurant.data.remote

import com.domain.restaurant.modules.restaurant.data.remote.RestaurantsService
import com.domain.restaurant.core.connection.ConnectionManager
import com.domain.restaurant.data.BaseDataSource
import javax.inject.Inject


class RestaurantsRemoteDataSource @Inject constructor(connectionManager: ConnectionManager, private val service: RestaurantsService) : BaseDataSource(connectionManager) {

    suspend fun fetchRestaurant(lat: Double,lon :Double,query:String) = getResult { service.fetchAllRestaurants(lat,lon,query,40) }


}
