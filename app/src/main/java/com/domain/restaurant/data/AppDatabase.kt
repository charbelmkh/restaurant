package com.domain.restaurant.data

import com.domain.restaurant.modules.restaurant.data.local.RestaurantsDao
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.domain.restaurant.Constant
import com.domain.restaurant.modules.restaurant.data.models.Favorites
import com.domain.restaurant.modules.restaurant.data.models.Restaurant


@Database(entities = [Restaurant::class, Favorites::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getRestaurantsDaoDao(): RestaurantsDao


    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, Constant.DATABASE_NAME)
                .build()
        }
    }
}
