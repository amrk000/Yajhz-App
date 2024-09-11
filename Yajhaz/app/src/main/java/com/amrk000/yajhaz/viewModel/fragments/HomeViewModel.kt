package com.amrk000.yajhaz.viewModel.fragments

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.amrk000.yajhaz.data.Repository
import com.amrk000.yajhaz.model.UserModel
import com.amrk000.yajhaz.model.response.CategoriesResponseModel
import com.amrk000.yajhaz.model.response.SellersResponseModel
import com.amrk000.yajhaz.model.response.UserResponseModel
import com.amrk000.yajhaz.util.SharedPrefManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(application: Application, repo: Repository) : AndroidViewModel(application) {
    private val repository = repo
    private val userProfile = MutableLiveData<UserResponseModel>()
    private val categories = MutableLiveData<CategoriesResponseModel>()
    private val popularSellers = MutableLiveData<SellersResponseModel>()
    private val trendingSellers = MutableLiveData<SellersResponseModel>()

    private val authToken  = "Bearer "+ SharedPrefManager.get(application).sessionToken
    private val language = SharedPrefManager.get(application).currentLang

    private var userData = SharedPrefManager.get(application).userData

    fun signOut(){
        SharedPrefManager.get(getApplication()).userSignedIn = false
    }

    fun setCachedUserData(user: UserModel){
        userData = user
        SharedPrefManager.get(getApplication()).userData = user
    }

    fun getCachedUserData(): UserModel?{
        return userData
    }

    fun getUserLat(): Double{
        return userData?.addresses?.get(0)?.lat?.toDouble()?: 0.0
    }

    fun getUserLong(): Double{
        return userData?.addresses?.get(0)?.lng?.toDouble()?: 0.0
    }

    fun getUserProfile(){
        viewModelScope.launch {
            try {
                val response = repository.getUserProfile(language, authToken)
                userProfile.value = response
            }
            catch (exception: Exception){
                exception.printStackTrace()

                if (exception is HttpException) {
                    val response = UserResponseModel(null)
                    response.code = exception.code()
                    response.message = exception.message()
                    userProfile.value = response

                    Log.e(
                        "Http Request Error: ",
                        "Code: ${exception.code()} | Message: ${exception.message()}"
                    )
                }

            }
        }
    }

    fun getCategories(){
        viewModelScope.launch {
            try {
                val response = repository.getCategories(language, authToken)
                categories.value = response
            }
            catch (exception: Exception){
                exception.printStackTrace()

                if (exception is HttpException) {
                    val response = CategoriesResponseModel(null)
                    response.code = exception.code()
                    response.message = exception.message()
                    categories.value = response

                    Log.e(
                        "Http Request Error: ",
                        "Code: ${exception.code()} | Message: ${exception.message()}"
                    )
                }

            }
        }
    }

    fun getPopularSellers(filter: Int){
        viewModelScope.launch {
            try {
                val response = repository.getPopularSellers(language, authToken, 29.1931, 30.6421, filter)
                popularSellers.value = response
            }
            catch (exception: Exception){
                exception.printStackTrace()

                if (exception is HttpException) {
                    val response = SellersResponseModel(null)
                    response.code = exception.code()
                    response.message = exception.message()
                    popularSellers.value = response

                    Log.e(
                        "Http Request Error: ",
                        "Code: ${exception.code()} | Message: ${exception.message()}"
                    )
                }

            }
        }
    }

    fun getTrendingSellers(filter: Int){
        viewModelScope.launch {
            try {
                val response = repository.getTrendingSellers(language, authToken, getUserLat(), getUserLong(), filter)
                trendingSellers.value = response
            }
            catch (exception: Exception){
                exception.printStackTrace()

                if (exception is HttpException) {
                    val response = SellersResponseModel(null)
                    response.code = exception.code()
                    response.message = exception.message()
                    trendingSellers.value = response

                    Log.e(
                        "Http Request Error: ",
                        "Code: ${exception.code()} | Message: ${exception.message()}"
                    )
                }

            }
        }
    }

    fun getUserProfileObserver(): MutableLiveData<UserResponseModel> {
        return userProfile
    }

    fun getCategoriesObserver(): MutableLiveData<CategoriesResponseModel> {
        return categories
    }

    fun getPopularSellersObserver(): MutableLiveData<SellersResponseModel> {
        return popularSellers
    }

    fun getTrendingSellersObserver(): MutableLiveData<SellersResponseModel> {
        return trendingSellers
    }

}