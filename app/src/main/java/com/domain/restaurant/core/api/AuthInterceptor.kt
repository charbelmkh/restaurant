package com.elifox.legocatalog.api

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * A {@see RequestInterceptor} that adds an auth token to requests
 */
class AuthInterceptor(private val accessToken: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("user-key", accessToken)
            .build()
        return chain.proceed(request)
    }
}
