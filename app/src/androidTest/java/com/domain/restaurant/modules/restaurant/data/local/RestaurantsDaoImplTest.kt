package com.domain.restaurant.modules.restaurant.data.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.domain.restaurant.core.utils.getRestaurant
import com.domain.restaurant.core.utils.getLiveDataValue
import com.domain.restaurant.data.AppDatabase
import com.domain.restaurant.modules.restaurant.data.models.Json4KotlinBase
import com.domain.restaurant.modules.restaurant.data.models.Restaurant
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class RestaurantsDaoImplTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var restaurantsDao: RestaurantsDao
    private lateinit var db: AppDatabase


    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        restaurantsDao = db.getRestaurantsDaoDao()
    }


    @Test
    @Throws(Exception::class)
    fun writeRestaurantsAndReadIt() {
        runBlocking {
            val listOfRestaurants = listOf(getRestaurant())
            restaurantsDao.insertAll(listOfRestaurants)
            val listOfRestaurantsFromDb: List<Restaurant> = restaurantsDao.getRestaurants().getLiveDataValue()
            assertThat(listOfRestaurantsFromDb, CoreMatchers.notNullValue())
        }

    }


    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

}