package com.domain.restaurant.data

import com.domain.restaurant.core.connection.ConnectionManager
import retrofit2.Response
import java.io.IOException

abstract class BaseDataSource constructor(val connectionManager: ConnectionManager) {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            if (!connectionManager.isNetworkAvailable()) {
                return error(Result.ErrorCode.NOT_CONNECTED)
            }
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.success(body)
            }
            return error(Result.ErrorCode.SERVER_UNEXPECTED_ERROR)
        } catch (e: IOException) {
            e.printStackTrace()
            return error(Result.ErrorCode.NO_INTERNET)
        } catch (e: Exception) {
            e.printStackTrace()
            return error(Result.ErrorCode.APP_UNEXPECTED_ERROR)
        }
    }

    private fun <T> error(code: Result.ErrorCode): Result<T> {
        return Result.error(code)
    }

}

