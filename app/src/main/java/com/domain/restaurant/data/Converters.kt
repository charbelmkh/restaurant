package com.domain.restaurant.data

import androidx.room.TypeConverter
import com.domain.restaurant.modules.restaurant.data.models.All_reviews
import com.domain.restaurant.modules.restaurant.data.models.Location
import com.domain.restaurant.modules.restaurant.data.models.UserRating
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromUserRatingToJson(userRating: UserRating): String = gson.toJson(userRating)

    @TypeConverter
    fun fromJsonToUserRating(json: String): UserRating =gson.fromJson(json, object : TypeToken<UserRating>() {}.type)

    @TypeConverter
    fun fromUserReviewToJson(userReviews: All_reviews): String = gson.toJson(userReviews)

    @TypeConverter
    fun fromJsonToUserReview(json: String): All_reviews =gson.fromJson(json, object : TypeToken<All_reviews>() {}.type)


    @TypeConverter
    fun fromLocation(location: Location): String = gson.toJson(location)

    @TypeConverter
    fun fromJsonToLocation(json: String): Location =gson.fromJson(json, object : TypeToken<Location>() {}.type)


    @TypeConverter
    fun fromListOfStringToJson(listOfString: List<String>): String = gson.toJson(listOfString)

    @TypeConverter
    fun fromJsonToListOfString(json: String): List<String> =gson.fromJson(json, object : TypeToken<List<String>>() {}.type)

    companion object {
        val gson = Gson()
    }
}
