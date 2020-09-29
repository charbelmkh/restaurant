package com.domain.restaurant.di.module

import com.domain.restaurant.modules.restaurant.data.local.RestaurantsDao
import android.util.Log
import com.domain.restaurant.modules.restaurant.data.remote.RestaurantsService
import com.domain.restaurant.core.connection.ConnectionManager
import com.domain.restaurant.modules.restaurant.data.remote.RestaurantsRemoteDataSource
import com.domain.restaurant.modules.restaurant.data.RestaurantsRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RestaurantsModule {
    companion object {
        const val TAG = "D-RestaurantsModule"
    }


    @Provides
    fun provideRestaurantsService(retrofit: Retrofit): RestaurantsService {
        Log.d(TAG, "provideRestaurantsService called")
        return retrofit.create(RestaurantsService::class.java)
    }

    @Provides
    fun provideRestaurantsRemoteDataSource(
        connectionManager: ConnectionManager,
        service: RestaurantsService
    ): RestaurantsRemoteDataSource {
        Log.d(TAG, "provideRestaurantsRemoteDataSource called")
        return RestaurantsRemoteDataSource(connectionManager, service)
    }

    @Singleton
    @Provides
    fun provideRestaurantsRepository(restaurantsDao: RestaurantsDao, dataSource: RestaurantsRemoteDataSource
    ): RestaurantsRepository {
        Log.d(TAG, "provideRestaurantsRepository called")
        return RestaurantsRepository(restaurantsDao, dataSource)
    }
}

