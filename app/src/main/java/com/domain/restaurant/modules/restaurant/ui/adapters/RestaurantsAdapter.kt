package com.domain.restaurant.modules.restaurant.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.domain.restaurant.R
import com.domain.restaurant.modules.restaurant.ui.model.FormattedRestaurantData
import com.domain.restaurant.core.ui.views.PriceTiersView
import com.domain.restaurant.core.ui.views.RatingStarsView
import com.domain.restaurant.core.ui.views.loadImageFromUrl



class RestaurantsAdapter(private val onItemInterAction: OnItemInterAction) : ListAdapter<FormattedRestaurantData, RestaurantsAdapter.ViewHolder>(DiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.image.setOnClickListener {
            onItemInterAction.onRowClicked(item)
        }
        holder.favorite.setOnClickListener {
            onItemInterAction.onAddToFavoriteClick(item)
        }
        holder.image.loadImageFromUrl(item.image)
        holder.favorite.setImageResource(item.is_favorite_icon)
        holder.name.text = item.name
        holder.cuisines.text = item.top_cuisines
        holder.ratingStarsView.setRating(item.average_rating)
        holder.reviews.text = item.total_reviews
        holder.priceTiers.setTiers(item.priceRange, item.price_tier_symbol)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.restaurants_list_item, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val root = view;
        val image: AppCompatImageView = view.findViewById(R.id.image)
        val favorite: AppCompatImageView = view.findViewById(R.id.favorite)
        val name: AppCompatTextView = view.findViewById(R.id.name)
        val cuisines: AppCompatTextView = view.findViewById(R.id.cuisines)
        val ratingStarsView: RatingStarsView = view.findViewById(R.id.ratingStarsView)
        val reviews: AppCompatTextView = view.findViewById(R.id.reviews)
        val priceTiers: PriceTiersView = view.findViewById(R.id.priceTiers)
    }

    interface OnItemInterAction {
        fun onRowClicked(rowData: FormattedRestaurantData)
        fun onAddToFavoriteClick(rowData: FormattedRestaurantData)

    }

    private class DiffCallback : DiffUtil.ItemCallback<FormattedRestaurantData>() {

        override fun areItemsTheSame(
            oldItem: FormattedRestaurantData,
            newItem: FormattedRestaurantData
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: FormattedRestaurantData,
            newItem: FormattedRestaurantData
        ): Boolean {
            return oldItem == newItem
        }
    }
}

/*class RestaurantsAdapter(
    private val onItemInterAction: OnItemInterAction,
    private val listOfRestaurants: MutableList<FormattedRestaurantData> = mutableListOf(),
) :
    RecyclerView.Adapter<RestaurantsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.restaurants_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listOfRestaurants[position]
        holder.favorite.setOnClickListener {
            onItemInterAction.onAddToFavoriteClick(item)
        }

        holder.image.setOnClickListener {
            onItemInterAction.onRowClicked(item)
        }
        holder.image.loadImageFromUrl(item.image)
        holder.favorite.setImageResource(item.is_favorite_icon)
        holder.name.text = item.name
        holder.cuisines.text = item.top_cuisines
        holder.ratingStarsView.setRating(item.average_rating)
        holder.reviews.text = item.total_reviews
        holder.priceTiers.setTiers(item.priceRange, item.price_tier_symbol)

    }

    fun overrideList(listOfRestaurants: List<FormattedRestaurantData>?) {
        this.listOfRestaurants.clear()
        listOfRestaurants?.let {
            this.listOfRestaurants.addAll(it)
        }
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int = listOfRestaurants.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val root = view;
        val image: AppCompatImageView = view.findViewById(R.id.image)
        val favorite: AppCompatImageView = view.findViewById(R.id.favorite)
        val name: AppCompatTextView = view.findViewById(R.id.name)
        val cuisines: AppCompatTextView = view.findViewById(R.id.cuisines)
        val ratingStarsView: RatingStarsView = view.findViewById(R.id.ratingStarsView)
        val reviews: AppCompatTextView = view.findViewById(R.id.reviews)
        val priceTiers: PriceTiersView = view.findViewById(R.id.priceTiers)
    }

    interface OnItemInterAction {
        fun onRowClicked(rowData: FormattedRestaurantData)
        fun onAddToFavoriteClick(rowData: FormattedRestaurantData)

    }

}*/




