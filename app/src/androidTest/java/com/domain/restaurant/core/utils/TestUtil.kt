package com.domain.restaurant.core.utils

import androidx.lifecycle.LiveData
import com.domain.restaurant.modules.restaurant.data.models.Json4KotlinBase
import com.domain.restaurant.modules.restaurant.data.models.Restaurant
import com.domain.restaurant.modules.restaurant.data.models.Restaurants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@Throws(InterruptedException::class)
fun <T> LiveData<T>.getLiveDataValue(): T {
    val data = arrayOfNulls<Any>(1)
    val latch = CountDownLatch(1)
    observeForever { o ->
        data[0] = o
        latch.countDown()
    }
    latch.await(2, TimeUnit.SECONDS)
    @Suppress("UNCHECKED_CAST")
    return data[0] as T
}

fun getRestaurant(): Restaurant {
    val gson = GsonBuilder().setLenient().create()
    val json ="{\"id\":16777961,\"apikey\":\"e275b72a6d74a8426f210b7ce5dbb248\",\"name\":\"The Spotted Pig\",\"url\":\"https://www.zomato.com/new-york-city/the-spotted-pig-greenwich-village?utm_source\\u003dapi_basic_user\\u0026utm_medium\\u003dapi\\u0026utm_campaign\\u003dv2.1\",\"switch_to_order_menu\":0,\"cuisines\":\"Burger, Bar Food\",\"timings\":\"12 Noon to 2 AM (Mon-Fri),11 AM to 2 AM (Sat-Sun)\",\"average_cost_for_two\":70,\"price_range\":4,\"currency\":\"\$\",\"opentable_support\":0,\"is_zomato_book_res\":0,\"mezzo_provider\":\"OTHER\",\"is_book_form_web_view\":0,\"book_form_web_view_url\":\"\",\"book_again_url\":\"\",\"thumb\":\"https://b.zmtcdn.com/data/pictures/chains/1/16777961/1c02d5846ed641b4a9d096b1486f30e2.png?fit\\u003daround%7C200%3A200\\u0026crop\\u003d200%3A200%3B%2A%2C%2A\",\"all_reviews_count\":191,\"photos_url\":\"https://www.zomato.com/new-york-city/the-spotted-pig-greenwich-village/photos?utm_source\\u003dapi_basic_user\\u0026utm_medium\\u003dapi\\u0026utm_campaign\\u003dv2.1#tabtop\",\"photo_count\":2744,\"menu_url\":\"https://www.zomato.com/new-york-city/the-spotted-pig-greenwich-village/menu?utm_source\\u003dapi_basic_user\\u0026utm_medium\\u003dapi\\u0026utm_campaign\\u003dv2.1\\u0026openSwipeBox\\u003dmenu\\u0026showMinimal\\u003d1#tabtop\",\"featured_image\":\"https://b.zmtcdn.com/data/pictures/chains/1/16777961/1c02d5846ed641b4a9d096b1486f30e2.png\",\"has_online_delivery\":0,\"is_delivering_now\":0,\"store_type\":\"\",\"include_bogo_offers\":true,\"deeplink\":\"zomato://restaurant/16777961\",\"is_table_reservation_supported\":0,\"has_table_booking\":0,\"events_url\":\"https://www.zomato.com/new-york-city/the-spotted-pig-greenwich-village/events#tabtop?utm_source\\u003dapi_basic_user\\u0026utm_medium\\u003dapi\\u0026utm_campaign\\u003dv2.1\",\"phone_numbers\":\"(212) 937-8518, (212) 620-0393, (212) 937-8518\",\"is_favorite\":false,\"user_rating\":{\"votes\":1243,\"aggregate_rating\":4.4,\"rating_text\":\"Very Good\",\"rating_color\":\"5BA829\"},\"highlights\":[\"Dinner\",\"Credit Card\",\"Lunch\",\"Serves Alcohol\",\"Michelin Starred\",\"Fullbar\",\"Indoor Seating\",\"Nightlife\",\"Gastro Pub\",\"Brunch\"],\"all_reviews\":{\"reviews\":[{\"review\":[]},{\"review\":[]},{\"review\":[]},{\"review\":[]},{\"review\":[]}]},\"offers\":[],\"location\":{\"address\":\"314 West 11th Street 10014\",\"locality\":\"Greenwich Village\",\"city\":\"New York City\",\"city_id\":280,\"latitude\":\"40.7355900000\",\"longitude\":\"-74.0065000000\",\"zipcode\":\"10014\",\"country_id\":216,\"locality_verbose\":\"Greenwich Village\"}}"
    val base: Restaurant = gson.fromJson(json, object : TypeToken<Restaurant>() {}.type)
    return base;
}


