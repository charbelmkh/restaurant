package com.domain.restaurant.modules.home.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val application: Application) : ViewModel() {

    private val locationLiveData = LocationLiveData(application)


    fun getLocationLiveData() = locationLiveData




}