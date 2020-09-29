package com.domain.restaurant.data
data class Result<out T>(val status: Status, val data: T?, val ex: ErrorCode?) {


    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    enum class ErrorCode {
        SERVER_UNEXPECTED_ERROR,
        APP_UNEXPECTED_ERROR,
        NOT_CONNECTED,
        NO_INTERNET,
        GPS_DISABLED,
    }
    companion object {
        fun <T> success(data: T): Result<T> {
            return Result(Status.SUCCESS, data, null)
        }

        fun <T> error(code: ErrorCode, data: T? = null): Result<T> {
            return Result(Status.ERROR, data, code)
        }

        fun <T> loading(data: T? = null): Result<T> {
            return Result(Status.LOADING, data, null)
        }
    }
}