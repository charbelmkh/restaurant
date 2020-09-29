package com.domain.restaurant.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.domain.restaurant.R
import com.domain.restaurant.data.Result
import com.domain.restaurant.core.ui.views.hide
import com.domain.restaurant.core.ui.views.show
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_base.*
import kotlinx.android.synthetic.main.progress_bar.*
import javax.inject.Inject

abstract class BaseFragment : Fragment(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory

    lateinit var snackBar: Snackbar


    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector;

    inline fun <reified T : ViewModel> injectViewModel(): T {
        return ViewModelProvider(this, viewModelProvider)[T::class.java]
    }

    override fun onResume() {
        super.onResume()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_base, container, false)
        val relative = view.findViewById(R.id.baseFragmentContent) as ViewGroup
        relative.addView(container)
        view.clearFindViewByIdCache()
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        snackBar = Snackbar.make(baseFragmentRoot, "", Snackbar.LENGTH_INDEFINITE);
    }
    override fun onPause() {
        super.onPause()
        snackBar.dismiss()
    }

    fun <T> handleLiveDataResults(
        result: Result<T>,
        success: (T?) -> Unit = {},
        retry: () -> Unit = {}
    ) {
        when (result.status) {
            Result.Status.LOADING -> {
                defaultProgressBar.show()
            }
            Result.Status.SUCCESS -> {
                defaultProgressBar.hide()
                success(result.data)

            }
            Result.Status.ERROR -> {
                defaultProgressBar.hide()
                handleErrorWithMessage(result,retry)
            }

        }
    }
    fun <T> handleErrorWithMessage(result: Result<T>, retry: () -> Unit) {
        result.ex?.let {
            when (it) {
                Result.ErrorCode.SERVER_UNEXPECTED_ERROR -> {
                    showSnackBarWithMessage(R.string.server_error_message, retry = retry)
                }
                Result.ErrorCode.APP_UNEXPECTED_ERROR -> {
                    showSnackBarWithMessage(R.string.app_error_message, retry = retry)
                }
                Result.ErrorCode.NOT_CONNECTED -> {
                    showSnackBarWithMessage(R.string.no_network_message, retry = retry)
                }
                Result.ErrorCode.NO_INTERNET -> {
                    showSnackBarWithMessage(R.string.no_internet_message, retry = retry)
                }
            }
        }
    }

    fun showSnackBarWithMessage(message: Int, retry: () -> Unit) {
        snackBar.setText(message)
        snackBar.setAction(R.string.retry) {
            retry()
        }
        snackBar.show()
    }
    fun showSnackBarWithMessage(message: Int) {
        snackBar.setText(message)
        snackBar.show()
    }

    companion object {
        private const val TAG = "BaseFragment"
    }

}

