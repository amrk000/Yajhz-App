package com.amrk000.yajhaz.viewModel.fragments

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.amrk000.yajhaz.data.Repository
import com.amrk000.yajhaz.model.request.SignUpRequestModel
import com.amrk000.yajhaz.model.response.UserResponseModel
import com.amrk000.yajhaz.util.SharedPrefManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(application: Application, repo: Repository) : AndroidViewModel(application) {
    private val repository = repo
    private val userData = MutableLiveData<UserResponseModel>()

    private val language = SharedPrefManager.get(application).currentLang

    fun signUpUser(requestBody: SignUpRequestModel){
        viewModelScope.launch {
            try {
                val response = repository.signUp(language,requestBody)
                userData.value = response
            }
            catch (exception: Exception){
                exception.printStackTrace()

                if (exception is HttpException) {
                    val response = UserResponseModel(null)
                    response.code = exception.code()
                    response.message = exception.message()
                    userData.value = response

                    Log.e(
                        "Http Request Error: ",
                        "Code: ${exception.code()} | Message: ${exception.message()}"
                    )
                }

            }
        }
    }

    fun getSignUpUserObserver(): MutableLiveData<UserResponseModel> {
        return userData
    }

}