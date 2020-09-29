package com.domain.restaurant.modules.home.ui

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.lifecycle.LiveData
import com.domain.restaurant.data.Result
import com.google.android.gms.location.*

class LocationLiveData(context: Context) : LiveData<Result<Location>>() {


    private var fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)
    private val locationManager: LocationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager;
    private var isCaptured = false

    private val locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            super.onLocationResult(locationResult)
            Log.d(TAG, "onLocationResult: $locationResult")
            locationResult?.let {
                for (location in it.locations) {
                    setResultData(Result.success(location))
                    break;
                }
            }


        }
    }

    @SuppressLint("MissingPermission")
    override fun onActive() {
        super.onActive()
        Log.d(TAG, "onActive: ")
        val isLocationEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (isLocationEnabled) {
            startLocationUpdates()
        } else {
            setResultData(Result.error(Result.ErrorCode.GPS_DISABLED))
        }

    }

    override fun onInactive() {
        super.onInactive()
        Log.d(TAG, "onInactive: ")
        removeLocationUpdates()
    }

    private fun removeLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        Log.d(TAG, "startLocationUpdates: ")
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                Log.d(TAG, "onActive: $it")
                setResultData(Result.success(it))

            }
        }
    }


    private fun setResultData(result: Result<Location>) {
        Log.d(TAG, "setLocationData: ${result}")
        if (!isCaptured) {
            value = result
            isCaptured = result.data != null;
        }
    }


    companion object {
        private const val TAG = "LocationLiveData"
        private const val ONE_MINUTE: Long = 60000
        val locationRequest: LocationRequest = LocationRequest.create().apply {
            interval = ONE_MINUTE
            fastestInterval = ONE_MINUTE / 4
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

    }
}