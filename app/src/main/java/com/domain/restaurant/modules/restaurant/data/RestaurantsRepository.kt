package com.domain.restaurant.modules.restaurant.data


import android.util.Log
import androidx.sqlite.db.SimpleSQLiteQuery
import com.domain.restaurant.data.resultLiveData
import com.domain.restaurant.modules.restaurant.data.local.RestaurantsDao
import com.domain.restaurant.modules.restaurant.data.models.Favorites
import com.domain.restaurant.modules.restaurant.data.models.Restaurant
import com.domain.restaurant.modules.restaurant.data.remote.RestaurantsRemoteDataSource
import javax.inject.Inject

class RestaurantsRepository @Inject constructor(private val restaurants: RestaurantsDao, private val remoteSource: RestaurantsRemoteDataSource
) {

    suspend fun addToFavorite(restaurants_id: Int, isFavorite: Boolean) {
        restaurants.changeFavoriteState(Favorites(restaurants_id, isFavorite))
    }


    fun getRestaurantById(restaurantId: Int) = restaurants.getRestaurantById(restaurantId)



    fun getRestaurants(lat: Double, lon: Double, query: String) = resultLiveData(
        databaseQuery = {
            val sql = "SELECT restaurants.*,favorites.is_favorite FROM restaurants LEFT JOIN favorites ON favorites.restaurants_ID=restaurants.id  WHERE name LIKE  ? ORDER BY restaurants.id ASC"
            Log.d(TAG, "getRestaurants: ${sql.replace("?","%$query%")}")
            restaurants.getRestaurants(SimpleSQLiteQuery(sql, arrayOf("%$query%")))
        },
        networkCall = { remoteSource.fetchRestaurant(lat, lon, query) },
        saveCallResult = {
            val listOfRestaurant: List<Restaurant> = it.restaurants.map { x -> x.restaurant }
            restaurants.insertAll(listOfRestaurant)
        },
    )


    companion object {
        val TAG = "RestaurantsRepository"
    }


}

