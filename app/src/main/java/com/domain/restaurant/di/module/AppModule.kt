package com.domain.restaurant.di.module

import com.domain.restaurant.modules.restaurant.data.local.RestaurantsDao
import android.app.Application
import android.util.Log
import com.domain.restaurant.Constant
import com.domain.restaurant.data.AppDatabase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
@Module(includes = [CoreModule::class])
class AppModule {

    companion object {
        const val TAG = "D-AppModule"
    }



    @Singleton
    @Provides
    fun provideRetrofitClient(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        Log.d(TAG, "provideRetrofitClient called")
        return Retrofit.Builder()
            .baseUrl(Constant.domain)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun provideDb(app: Application) = AppDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideRestaurantsDaoDao(db: AppDatabase): RestaurantsDao = db.getRestaurantsDaoDao()



}
