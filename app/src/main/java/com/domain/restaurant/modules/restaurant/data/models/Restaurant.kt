package com.domain.restaurant.modules.restaurant.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "restaurants")
data class Restaurant(

    @PrimaryKey @ColumnInfo(name = "id")
    @field:SerializedName("id") val id: Int,

    @field:SerializedName("apikey") val apiapikey: String?,
    @field:SerializedName("name") val name: String?,
    @field:SerializedName("url") val url: String?,
    @field:SerializedName("switch_to_order_menu") val switch_to_order_menu: Int?,
    @field:SerializedName("cuisines") val cuisines: String?,
    @field:SerializedName("timings") val timings: String?,
    @field:SerializedName("average_cost_for_two") val average_cost_for_two: Int?,
    @field:SerializedName("price_range") val price_range: Int?,
    @field:SerializedName("currency") val currency: String?,

    @field:SerializedName("opentable_support") val opentable_support: Int?,
    @field:SerializedName("is_zomato_book_res") val is_zomato_book_res: Int?,
    @field:SerializedName("mezzo_provider") val mezzo_provider: String?,
    @field:SerializedName("is_book_form_web_view") val is_book_form_web_view: Int?,
    @field:SerializedName("book_form_web_view_url") val book_form_web_view_url: String?,
    @field:SerializedName("book_again_url") val book_again_url: String?,
    @field:SerializedName("thumb") val thumb: String?,

    @field:SerializedName("all_reviews_count") val all_reviews_count: Int?,
    @field:SerializedName("photos_url") val photos_url: String?,
    @field:SerializedName("photo_count") val photo_count: Int?,
    @field:SerializedName("menu_url") val menu_url: String?,
    @field:SerializedName("featured_image") val featured_image: String?,
    @field:SerializedName("has_online_delivery") val has_online_delivery: Int?,
    @field:SerializedName("is_delivering_now") val is_delivering_now: Int?,
    @field:SerializedName("store_type") val store_type: String?,
    @field:SerializedName("include_bogo_offers") val include_bogo_offers: Boolean?,
    @field:SerializedName("deeplink") val deeplink: String?,
    @field:SerializedName("is_table_reservation_supported") val is_table_reservation_supported: Int?,
    @field:SerializedName("has_table_booking") val has_table_booking: Int?,
    @field:SerializedName("events_url") val events_url: String?,
    @field:SerializedName("phone_numbers") val phone_numbers: String?,
    @field:SerializedName("is_favorite") val is_favorite: Boolean,
    @field:SerializedName("user_rating") val user_rating: UserRating?,
    @field:SerializedName("highlights") val highlights : List<String>?,
    @field:SerializedName("all_reviews") val all_reviews : All_reviews?,
    @field:SerializedName("offers") val offers : List<String>?,
    @field:SerializedName("location") val location : Location,
)

