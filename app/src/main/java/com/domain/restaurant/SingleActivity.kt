package com.domain.restaurant

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.TaskStackBuilder
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.domain.restaurant.core.ui.views.hide
import com.domain.restaurant.core.ui.views.show
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_single.*
import javax.inject.Inject

class SingleActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration
    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)
        setSupportActionBar(toolbar)

        toolbar.hide()

        appBarConfiguration = setupAppBarConfiguration()
        navController = findNavController(R.id.nav_host_fragment)

        setupActionBarWithNavController(navController, appBarConfiguration)


        setNavigationListener(navController)


    }

    private fun setupAppBarConfiguration(): AppBarConfiguration {
        val topLevelDestinations = setOf(
            R.id.home_dest,
            R.id.restaurants_dest
        )
        return AppBarConfiguration(topLevelDestinations)

    }

    private fun setNavigationListener(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->

            try {
                val dest = resources.getResourceName(destination.id)
                Log.d(Companion.TAG, dest)
            } catch (e: Resources.NotFoundException) {
                e.printStackTrace()
            }

            when (destination.id) {
                R.id.home_dest -> {
                    toolbar.hide()
                }
                R.id.restaurants_dest -> {
                    toolbar.show()
                }
                R.id.restaurants_details_dest -> {
                    //toolbar.hide()
                }
            }

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)

    }

    companion object {
        private const val TAG = "SingleActivity"
    }

}