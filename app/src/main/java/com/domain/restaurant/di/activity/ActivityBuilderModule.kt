package com.domain.restaurant.di.activity


import com.domain.restaurant.SingleActivity
import com.domain.restaurant.di.fragment.FragmentBuilderModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module()
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [  FragmentBuilderModule::class ])
    abstract fun contributeSingleActivity(): SingleActivity

}