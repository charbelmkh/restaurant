package com.domain.restaurant.modules.restaurant.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "favorites")
data class Favorites(
    @PrimaryKey @ColumnInfo(name = "restaurants_id")
    @field:SerializedName("restaurants_id")val restaurants_ID: Int = 0,
    @field:SerializedName("is_favorite") val is_favorite: Boolean,

)

