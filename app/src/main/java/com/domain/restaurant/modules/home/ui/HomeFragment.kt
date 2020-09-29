package com.domain.restaurant.modules.home.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import com.domain.restaurant.R
import com.domain.restaurant.core.ui.BaseFragment
import com.domain.restaurant.data.Result

class HomeFragment : BaseFragment() {

    companion object {
        const val TAG = "HomeFragment"
        const val LOCATION_PERMISSION_REQUEST_CODE = 2000
    }


    private lateinit var viewModel: HomeViewModel
    lateinit var dialog: AlertDialog

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog = createGpsDialog()
        viewModel = injectViewModel()
        prepRequestLocationUpdates()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return super.onCreateView(
            inflater,
            inflater.inflate(R.layout.fragment_home, container, false) as ViewGroup,
            savedInstanceState
        )
    }

    private fun prepRequestLocationUpdates() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            requestLocationUpdates()
        } else {
            val permissionRequest = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
            requestPermissions(permissionRequest, LOCATION_PERMISSION_REQUEST_CODE)
        }
    }

    private fun requestLocationUpdates() {
        viewModel.getLocationLiveData().observe(viewLifecycleOwner, { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    dialog.dismiss()
                    result.data?.let {
                        val action = HomeFragmentDirections.fromSplashToRestaurantsAction(it)
                        NavHostFragment.findNavController(this).navigate(action)
                    }

                }
                Result.Status.ERROR -> {
                    dialog.show()
                }

            }


        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    requestLocationUpdates()
                } else {
                    createPermissionDialogDialog().show()
                }
            }
            else -> {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            }
        }
    }

    private fun createGpsDialog(): AlertDialog {
        return AlertDialog.Builder(requireActivity())
            .setTitle(R.string.info)
            .setMessage(R.string.gps_message)
            .setCancelable(false)
            .setPositiveButton(
                R.string.settings
            ) { _, _ ->
                startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
            .setNegativeButton(
                R.string.exit
            ) { _, _ -> requireActivity().finish() }
            .create()
    }
    private fun createPermissionDialogDialog(): AlertDialog {
        return AlertDialog.Builder(requireActivity())
            .setTitle(R.string.info)
            .setMessage(R.string.permission_dialog)
            .setCancelable(false)
            .setPositiveButton(
                R.string.settings
            ) { _, _ ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,Uri.fromParts("package", requireActivity().packageName, null))
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
            .setNegativeButton(
                R.string.exit
            ) { _, _ -> requireActivity().finish() }
            .create()
    }


}