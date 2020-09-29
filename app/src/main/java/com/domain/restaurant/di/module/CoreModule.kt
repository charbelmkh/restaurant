package com.domain.restaurant.di.module

import android.app.Application
import android.util.Log
import com.domain.restaurant.BuildConfig
import com.domain.restaurant.core.connection.ConnectionManagerImpl
import com.domain.restaurant.Constant
import com.domain.restaurant.core.connection.ConnectionManager
import com.elifox.legocatalog.api.AuthInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import javax.inject.Singleton

@Module
class CoreModule {
    companion object {
        const val TAG = "D-CoreModule"
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        Log.d(TAG, "provideGson called")
        return GsonBuilder().disableHtmlEscaping().create()
    }

    @Singleton
    @Provides
    fun provideConnectionManager(context: Application): ConnectionManager {
        Log.d(TAG, "provideConnectionManager called")
        return ConnectionManagerImpl(context)
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        Log.d(TAG, "provideOkHttpClient called")
        val logger =HttpLoggingInterceptor().apply { level = if (BuildConfig.DEBUG) BODY else NONE }

        val builder = OkHttpClient.Builder();
        if (BuildConfig.PIN_CERTIFICATE) {
            val certificatePinner = getCertificatePinner()
            builder.certificatePinner(certificatePinner)
        }
        builder.addInterceptor(logger)
        builder.addInterceptor(AuthInterceptor(BuildConfig.API_KEY))
        return builder.build();
    }

    private fun getCertificatePinner(): CertificatePinner = CertificatePinner.Builder()
        .add("*.${Constant.hostname}", Constant.certificate)
        .build()

}
