
package com.domain.restaurant.modules.restaurant.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.domain.restaurant.modules.restaurant.data.models.Favorites
import com.domain.restaurant.modules.restaurant.data.models.Restaurant


@Dao
interface RestaurantsDao {

    @RawQuery(observedEntities = [Restaurant::class, Favorites::class])
    fun getRestaurants(sortQuery: SupportSQLiteQuery?): LiveData<List<Restaurant>>

    @Query(value="SELECT restaurants.*,favorites.is_favorite FROM restaurants LEFT JOIN favorites ON favorites.restaurants_ID=restaurants.id")
    fun getRestaurants(): LiveData<List<Restaurant>>

    @Query("SELECT restaurants.*,favorites.is_favorite FROM restaurants LEFT JOIN favorites ON favorites.restaurants_ID=restaurants.id where id=:id")
    fun getRestaurantById(id: Int): LiveData<Restaurant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(restaurants: List<Restaurant>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(restaurant: Restaurant)


    @Query("DELETE FROM restaurants")
    suspend fun clearAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun changeFavoriteState(favorite: Favorites)

}