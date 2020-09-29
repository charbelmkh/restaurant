package com.domain.restaurant.modules.restaurant.ui.viewmodel

import androidx.lifecycle.*
import com.domain.restaurant.R
import com.domain.restaurant.data.Result
import com.domain.restaurant.modules.restaurant.data.RestaurantsRepository
import com.domain.restaurant.modules.restaurant.data.models.Restaurant
import com.domain.restaurant.modules.restaurant.data.models.Reviews
import com.domain.restaurant.modules.restaurant.ui.model.FormattedRestaurantData
import kotlinx.coroutines.launch
import javax.inject.Inject

class RestaurantsViewModel @Inject constructor(private val repo: RestaurantsRepository) : ViewModel() {

    private var restaurantsLiveDataPreviousSource: LiveData<Result<List<Restaurant>>>? = null


    var restaurantId: Int = -1

    val restaurantsLiveData: MediatorLiveData<Result<List<FormattedRestaurantData>>> by lazy { MediatorLiveData<Result<List<FormattedRestaurantData>>>() }

    val restaurantLiveData by lazy {
        Transformations.map(repo.getRestaurantById(restaurantId.toInt())) {
            it?.let {
                convertToFormattedData(it)
            }
        }
    }

    fun fetchFormattedRestaurants(
        lat: Double,
        lon: Double,
        query: String
    ) {
        removeOldSource()
        restaurantsLiveDataPreviousSource = repo.getRestaurants(lat, lon, query);
        restaurantsLiveData.addSource(restaurantsLiveDataPreviousSource!!, Observer {
            when (it.status) {
                Result.Status.SUCCESS -> {
                    val data = convertToFormattedData(it.data)
                    restaurantsLiveData.value = data
                }
                Result.Status.ERROR -> {
                    restaurantsLiveData.value = Result.error(it.ex!!)
                }
                Result.Status.LOADING -> {
                    restaurantsLiveData.value = Result.loading()
                }
            }


        })

    }

    private fun removeOldSource() {
        restaurantsLiveDataPreviousSource?.let {
            restaurantsLiveData.removeSource(it)
        }
    }

    fun addToFavorite(restaurants_id: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            repo.addToFavorite(restaurants_id, isFavorite)
        }
    }


    private fun convertToFormattedData(restaurant: List<Restaurant>?): Result<List<FormattedRestaurantData>> {
        val list = mutableListOf<FormattedRestaurantData>()
        restaurant?.let {
            for (rest in it) {
                list.add(convertToFormattedData(rest))
            }
        }

        return Result.success(list)
    }

    private fun convertToFormattedData(restaurant: Restaurant): FormattedRestaurantData {
        val id = restaurant.id
        val name = restaurant.name ?: ""
        val image = restaurant.thumb ?: ""
        val average_rating: Int =
            if (restaurant.user_rating?.aggregate_rating == null) 0 else restaurant.user_rating.aggregate_rating.toInt()
        val total_reviews = restaurant.all_reviews_count.toString()
        val top_cuisines = restaurant.cuisines ?: ""
        val price_tier = restaurant.price_range ?: 0
        val price_tier_symbol = "$"
        val favorite_icon =
            if (restaurant.is_favorite) (R.drawable.ic_heart_red) else R.drawable.ic_heart_white
        val is_favorite = restaurant.is_favorite
        val highlights = mutableListOf<String>()
        restaurant.highlights?.let {
            highlights.addAll(it)
        }
        val listOfReviews = mutableListOf<Reviews>()
        restaurant.all_reviews?.let {
            it.reviews?.let {
                listOfReviews.addAll(it)
            }
        }
        val phoneNumber: String = restaurant.phone_numbers ?: ""
        return FormattedRestaurantData(
            id,
            name,
            image,
            average_rating,
            total_reviews,
            top_cuisines,
            price_tier,
            price_tier_symbol,
            favorite_icon,
            is_favorite,
            highlights,
            listOfReviews,
            restaurant.location,
            phoneNumber

        )
    }


}