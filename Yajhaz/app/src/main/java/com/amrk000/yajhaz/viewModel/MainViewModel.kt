package com.amrk000.yajhaz.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.amrk000.yajhaz.util.SharedPrefManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {
    private val isUserSignedIn = SharedPrefManager.get(application).userSignedIn

    fun isUserSignedIn(): Boolean{
        return isUserSignedIn
    }

}