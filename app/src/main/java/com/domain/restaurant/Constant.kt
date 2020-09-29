package com.domain.restaurant


class Constant {
    companion object {
        ////////////////////////////WEB//////////////////////////////////////////

        //val hostname="192.168.8.105:8080"
        //val domain="http://$hostname/"
        const val subdomain = BuildConfig.SUBDOMAIN
        const val protocol = BuildConfig.PROTOCOL
        const val hostname = BuildConfig.HOST_NAME
        const val port = BuildConfig.PORT
        const val certificate = BuildConfig.CERTIFICATE
        const val domain = "$protocol://$subdomain$hostname:$port/"
        const val DATABASE_NAME = "app-db"
        const val configPrefName = "app-config"

        const val currencySymbolKey = "currencySymbolKey"
        const val maxPriceTiersKey = "maxPriceTiersKey"
        const val maxResultsCacheTimeSecondsKey = "maxResultsCacheTimeSecondsKey"
        const val addToFavoritesEnabled = "addToFavoritesEnabled"
        const val lastTimeUpdated = "lastTimeUpdated"

    }
}