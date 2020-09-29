package com.domain.restaurant.di.fragment

import com.domain.restaurant.modules.restaurant.ui.fragments.RestaurantDetailsFragment
import com.domain.restaurant.modules.restaurant.ui.fragments.RestaurantsFragment
import com.domain.restaurant.modules.home.ui.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module()
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector()
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector()
    abstract fun contributeRestaurantsFragment(): RestaurantsFragment

    @ContributesAndroidInjector()
    abstract fun contributeRestaurantDetailsFragment(): RestaurantDetailsFragment



}