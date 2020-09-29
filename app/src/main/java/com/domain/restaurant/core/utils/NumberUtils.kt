package com.domain.restaurant.core.utils


class NumberUtils {

    companion object {
        fun getSafeInt(number: String?): Int =
            number?.let {
                it.toInt()
            } ?: run {
                0
            }

    }
}