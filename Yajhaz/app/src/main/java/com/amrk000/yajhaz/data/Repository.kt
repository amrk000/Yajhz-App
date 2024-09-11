package com.amrk000.yajhaz.data

import com.amrk000.yajhaz.data.remote.RetrofitInterface
import com.amrk000.yajhaz.model.request.SignInRequestModel
import com.amrk000.yajhaz.model.request.SignUpRequestModel
import com.amrk000.yajhaz.model.response.CategoriesResponseModel
import com.amrk000.yajhaz.model.response.SellersResponseModel
import com.amrk000.yajhaz.model.response.UserResponseModel
import retrofit2.Retrofit
import javax.inject.Inject

class Repository @Inject constructor(private val retrofit: Retrofit){

    //Remote Data:

    //User Account
    suspend fun signIn(language: String, requestBody: SignInRequestModel): UserResponseModel {
        return retrofit.create(RetrofitInterface::class.java).signIn(language, requestBody)
    }

    suspend fun signUp(language: String, requestBody: SignUpRequestModel): UserResponseModel {
        return retrofit.create(RetrofitInterface::class.java).signUp(language, requestBody)
    }

    suspend fun getUserProfile(language: String, authToken: String): UserResponseModel {
        return retrofit.create(RetrofitInterface::class.java).getUserProfile(language, authToken)
    }

    //Home
    suspend fun getCategories(language: String, authToken: String): CategoriesResponseModel {
      return retrofit.create(RetrofitInterface::class.java).getCategories(language, authToken)
    }

    suspend fun getPopularSellers(language: String, authToken: String, lat: Double, lng: Double, filter: Int): SellersResponseModel {
        return retrofit.create(RetrofitInterface::class.java).getPopularSellers(language, authToken, lat, lng, filter)
    }

    suspend fun getTrendingSellers(language: String, authToken: String, lat: Double, lng: Double, filter: Int): SellersResponseModel {
        return retrofit.create(RetrofitInterface::class.java).getTrendingSellers(language, authToken, lat, lng, filter)
    }

}