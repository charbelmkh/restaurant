package com.domain.restaurant.modules.restaurant.ui.model

import com.domain.restaurant.modules.restaurant.data.models.All_reviews
import com.domain.restaurant.modules.restaurant.data.models.Location
import com.domain.restaurant.modules.restaurant.data.models.Reviews

data class FormattedRestaurantData(
    val id: Int,
    val name: String,
    val image: String,
    val average_rating: Int,
    val total_reviews: String,
    val top_cuisines: String,
    val priceRange: Int,
    val price_tier_symbol: String,
    val is_favorite_icon: Int,
    val is_favorite: Boolean,
    val highlights: List<String>,
    val all_reviews: List<Reviews>,
    val location: Location,
    val phoneNumber: String


)

