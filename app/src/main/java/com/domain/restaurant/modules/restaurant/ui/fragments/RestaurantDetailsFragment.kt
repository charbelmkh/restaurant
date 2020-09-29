package com.domain.restaurant.modules.restaurant.ui.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.domain.restaurant.R
import com.domain.restaurant.core.ui.BaseFragment
import com.domain.restaurant.core.ui.views.loadImageFromUrl
import com.domain.restaurant.modules.restaurant.ui.viewmodel.RestaurantsViewModel
import com.domain.restaurant.modules.restaurant.ui.model.FormattedRestaurantData
import kotlinx.android.synthetic.main.fragment_restaurant_details.*
import kotlinx.android.synthetic.main.price_range_holder.*
import kotlinx.android.synthetic.main.restaurants_list_item.*
import java.util.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class RestaurantDetailsFragment : BaseFragment(){

    companion object {
        val TAG = "RestaurantDetailsFra"
    }


    val args: RestaurantDetailsFragmentArgs by navArgs()

    private lateinit var viewModel: RestaurantsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return super.onCreateView(
            inflater,
            inflater.inflate(R.layout.fragment_restaurant_details, container, false) as ViewGroup,
            savedInstanceState
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = injectViewModel()
        viewModel.restaurantId = args.restaurantsId
        viewModel.restaurantLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                displayRestaurantDetails(it)
            }
        })
    }

    private fun displayRestaurantDetails(restaurant: FormattedRestaurantData) {
        requireActivity().title = restaurant.name
        favorite.setOnClickListener {
            viewModel.addToFavorite(restaurant.id, !restaurant.is_favorite)
        }
        image.loadImageFromUrl(restaurant.image)
        favorite.setImageResource(restaurant.is_favorite_icon)
        name.text = restaurant.name
        cuisines.text = restaurant.top_cuisines
        ratingStarsView.setRating(restaurant.average_rating)
        reviews.text = restaurant.total_reviews
        priceTiers.setTiers(restaurant.priceRange, restaurant.price_tier_symbol)
        address.text = restaurant.location.toString()
        mapButton.setOnClickListener {
            try {
                val uri: String = String.format(
                    Locale.ENGLISH,
                    "geo:%s,%s",
                    restaurant.location.latitude,
                    restaurant.location.longitude
                )
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                requireActivity().startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        highlights.text = restaurant.highlights.toString()
        phoneNumber.setOnClickListener {
            val clipboard: ClipboardManager =
                requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("phone", restaurant.phoneNumber)
            clipboard.setPrimaryClip(clip)
            showSnackBarWithMessage(R.string.number_copied)

        }
    }

}