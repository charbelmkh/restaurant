package com.domain.restaurant.core.utils

import android.os.Build


object BuildUtils {
    @JvmStatic val isAtLeast24Api: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N

    @JvmStatic  val isAtLeast17Api: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1

    @JvmStatic val isAtLeast23Api: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

    @JvmStatic val isAtLeast21Api: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
}