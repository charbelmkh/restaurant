package com.domain.restaurant.modules.restaurant.ui.fragments

import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.domain.restaurant.R
import com.domain.restaurant.core.ui.BaseFragment
import com.domain.restaurant.modules.restaurant.ui.viewmodel.RestaurantsViewModel
import com.domain.restaurant.modules.restaurant.ui.adapters.RestaurantsAdapter
import com.domain.restaurant.modules.restaurant.ui.model.FormattedRestaurantData
import kotlinx.android.synthetic.main.fragment_restaurants.*
import kotlinx.android.synthetic.main.recycle_list_view.*

class RestaurantsFragment : BaseFragment(), RestaurantsAdapter.OnItemInterAction {

    companion object {
        const val TAG = "RestaurantsFragment"

    }

    private lateinit var viewModel: RestaurantsViewModel

    private var query: String = ""

    val args: RestaurantsFragmentArgs by navArgs()


    val adapter: RestaurantsAdapter by lazy {
        RestaurantsAdapter(
            this

        )
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        return super.onCreateView(
            inflater,
            inflater.inflate(R.layout.fragment_restaurants, container, false) as ViewGroup,
            savedInstanceState
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()
        requireActivity().title = requireActivity().getString(R.string.nearby)
        Log.d(TAG, "onResume")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")
        listView.adapter = adapter
        viewModel = injectViewModel()
        viewModel.restaurantsLiveData.observe(viewLifecycleOwner, Observer {
            swipeToRefresh.isRefreshing = false

            handleLiveDataResults(it,
                success = {
                    if(it==null || it.isEmpty()){
                        showSnackBarWithMessage(R.string.no_data_found)
                    }
                    Log.d(TAG, "List Size: ${it?.size}")
                    adapter.submitList(it)
                }, retry = {
                    loadRestaurants()
                })

        })
        swipeToRefresh.setOnRefreshListener {
            loadRestaurants()
        }
        if (adapter.itemCount == 0)
            loadRestaurants()
    }




    override fun onRowClicked(rowData: FormattedRestaurantData) {
        val action = RestaurantsFragmentDirections.fromRestaurantsToDetailsAction(rowData.id);
        NavHostFragment.findNavController(this).navigate(action)
    }

    override fun onAddToFavoriteClick(rowData: FormattedRestaurantData) {
        viewModel.addToFavorite(rowData.id, !rowData.is_favorite)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
        val myActionMenuItem: MenuItem = menu.findItem(R.id.action_search)
        val searchView = myActionMenuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(search: String): Boolean {
                query = search
                loadRestaurants()
                return false
            }

            override fun onQueryTextChange(s: String?): Boolean {
                return false
            }
        })
        myActionMenuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(menuItem: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(menuItem: MenuItem?): Boolean {
                query = ""
                loadRestaurants()
                return true
            }
        })
    }

    private fun loadRestaurants() {
        val location = args.location as Location
        viewModel.fetchFormattedRestaurants(location.latitude, location.longitude, query)
    }
}