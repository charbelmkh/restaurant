package com.domain.restaurant.core.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.domain.restaurant.modules.restaurant.data.models.All_reviews
import com.domain.restaurant.modules.restaurant.data.models.Location
import com.domain.restaurant.modules.restaurant.data.models.UserRating
import com.domain.restaurant.modules.restaurant.data.remote.RestaurantsService
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class RestaurantsServiceTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: RestaurantsService

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RestaurantsService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun requestRestaurants() {
        runBlocking {
            enqueueResponse("restaurants.json", mockWebServer = mockWebServer)
            val resultResponse = service.fetchAllRestaurants(0.0, 0.0, "", 0).body()
            val request = mockWebServer.takeRequest()
            assertNotNull(resultResponse)
            assertThat(request.path, CoreMatchers.`is`("/api/v2.1/search?lat=0.0&lon=0.0&q=&count=0"))

        }
    }

    @Test
    fun getRestaurantsItem() {
        runBlocking {
            enqueueResponse("restaurants.json", mockWebServer = mockWebServer)
            val resultResponse = service.fetchAllRestaurants(0.0, 0.0, "", 0).body()
            val response = resultResponse!!
            print(Gson().toJson(response.restaurants[0].restaurant))
            assertThat(response.results_found, CoreMatchers.equalTo(1))
            assertThat(response.results_shown, CoreMatchers.equalTo(1))
            assertThat(response.results_start, CoreMatchers.equalTo(1))
            assertThat(response.restaurants.size, CoreMatchers.equalTo(1))
            val restaurant = response.restaurants[0].restaurant
            assertThat(restaurant.id, CoreMatchers.equalTo(16777961))
            assertThat(restaurant.name, CoreMatchers.equalTo("The Spotted Pig"))
            assertThat(
                restaurant.url,
                CoreMatchers.equalTo("https://www.zomato.com/new-york-city/the-spotted-pig-greenwich-village?utm_source=api_basic_user&utm_medium=api&utm_campaign=v2.1")
            )
            assertThat(restaurant.switch_to_order_menu, CoreMatchers.equalTo(0))
            assertThat(restaurant.cuisines, CoreMatchers.equalTo("Burger, Bar Food"))
            assertThat(
                restaurant.timings,
                CoreMatchers.equalTo("12 Noon to 2 AM (Mon-Fri),11 AM to 2 AM (Sat-Sun)")
            )
            assertThat(restaurant.average_cost_for_two, CoreMatchers.equalTo(70))
            assertThat(restaurant.price_range, CoreMatchers.equalTo(4))
            assertThat(restaurant.currency, CoreMatchers.equalTo("$"))
            assertThat(restaurant.opentable_support, CoreMatchers.equalTo(0))
            assertThat(restaurant.is_zomato_book_res, CoreMatchers.equalTo(0))
            assertThat(restaurant.mezzo_provider, CoreMatchers.equalTo("OTHER"))
            assertThat(restaurant.is_book_form_web_view, CoreMatchers.equalTo(0))
            assertThat(restaurant.book_form_web_view_url, CoreMatchers.equalTo(""))
            assertThat(restaurant.book_again_url, CoreMatchers.equalTo(""))
            assertThat(
                restaurant.thumb,
                CoreMatchers.equalTo("https://b.zmtcdn.com/data/pictures/chains/1/16777961/1c02d5846ed641b4a9d096b1486f30e2.png?fit=around%7C200%3A200&crop=200%3A200%3B%2A%2C%2A")
            )
            assertThat(restaurant.all_reviews_count, CoreMatchers.equalTo(191))
            assertThat(
                restaurant.photos_url,
                CoreMatchers.equalTo("https://www.zomato.com/new-york-city/the-spotted-pig-greenwich-village/photos?utm_source=api_basic_user&utm_medium=api&utm_campaign=v2.1#tabtop")
            )
            assertThat(restaurant.photo_count, CoreMatchers.equalTo(2744))
            assertThat(
                restaurant.menu_url,
                CoreMatchers.equalTo("https://www.zomato.com/new-york-city/the-spotted-pig-greenwich-village/menu?utm_source=api_basic_user&utm_medium=api&utm_campaign=v2.1&openSwipeBox=menu&showMinimal=1#tabtop")
            )
            assertThat(
                restaurant.featured_image,
                CoreMatchers.equalTo("https://b.zmtcdn.com/data/pictures/chains/1/16777961/1c02d5846ed641b4a9d096b1486f30e2.png")
            )
            assertThat(restaurant.has_online_delivery, CoreMatchers.equalTo(0))
            assertThat(restaurant.is_delivering_now, CoreMatchers.equalTo(0))
            assertThat(restaurant.store_type, CoreMatchers.equalTo(""))
            assertThat(restaurant.include_bogo_offers, CoreMatchers.equalTo(true))
            assertThat(restaurant.deeplink, CoreMatchers.equalTo("zomato://restaurant/16777961"))
            assertThat(restaurant.is_table_reservation_supported, CoreMatchers.equalTo(0))
            assertThat(restaurant.has_table_booking, CoreMatchers.equalTo(0))
            assertThat(
                restaurant.events_url,
                CoreMatchers.equalTo("https://www.zomato.com/new-york-city/the-spotted-pig-greenwich-village/events#tabtop?utm_source=api_basic_user&utm_medium=api&utm_campaign=v2.1")
            )
            assertThat(
                restaurant.phone_numbers,
                CoreMatchers.equalTo("(212) 937-8518, (212) 620-0393, (212) 937-8518")
            )
            assertThat(restaurant.user_rating, CoreMatchers.notNullValue())
            assertThat(restaurant.highlights, CoreMatchers.notNullValue())
            assertThat(restaurant.all_reviews, CoreMatchers.notNullValue())
            assertThat(restaurant.offers, CoreMatchers.notNullValue())
            assertThat(restaurant.location, CoreMatchers.notNullValue())


        }
    }


    companion object {
        fun enqueueResponse(
            fileName: String,
            headers: Map<String, String> = emptyMap(),
            mockWebServer: MockWebServer
        ) {
            val inputStream = javaClass.classLoader?.getResourceAsStream("api-response/$fileName")
            val source = inputStream?.source()?.buffer()
            val mockResponse = MockResponse()
            for ((key, value) in headers) {
                mockResponse.addHeader(key, value)
            }
            mockWebServer.enqueue(
                mockResponse.setBody(source?.readString(Charsets.UTF_8)!!)
            )
        }
    }

}