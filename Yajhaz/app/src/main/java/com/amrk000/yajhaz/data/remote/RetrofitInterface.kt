package com.amrk000.yajhaz.data.remote

import com.amrk000.yajhaz.model.request.SignInRequestModel
import com.amrk000.yajhaz.model.request.SignUpRequestModel
import com.amrk000.yajhaz.model.response.CategoriesResponseModel
import com.amrk000.yajhaz.model.response.SellersResponseModel
import com.amrk000.yajhaz.model.response.UserResponseModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitInterface {

    @Headers("Accept: application/json")

    //User Account
    @POST("login")
    suspend fun signIn(
        @Header("lang") language: String,
        @Body requestBody: SignInRequestModel
    ): UserResponseModel

    @POST("client-register")
    suspend fun signUp(
        @Header("lang") language: String,
        @Body requestBody: SignUpRequestModel
    ): UserResponseModel

    @GET("client-profile")
    suspend fun getUserProfile(
        @Header("lang") language: String,
        @Header("Authorization") authToken: String
    ): UserResponseModel

    //Home
    @GET("base-categories")
    suspend fun getCategories(
        @Header("lang") language: String,
        @Header("Authorization") authToken: String
    ): CategoriesResponseModel

    @GET("popular-sellers")
    suspend fun getPopularSellers(
        @Header("lang") language: String,
        @Header("Authorization") authToken: String,
        @Query("lat") lat: Double,
        @Query("lng") lng: Double,
        @Query("filter") filter: Int,
    ): SellersResponseModel

    @GET("trending-sellers")
    suspend fun getTrendingSellers(
        @Header("lang") language: String,
        @Header("Authorization") authToken: String,
        @Query("lat") lat: Double,
        @Query("lng") lng: Double,
        @Query("filter") filter: Int,
    ): SellersResponseModel

}