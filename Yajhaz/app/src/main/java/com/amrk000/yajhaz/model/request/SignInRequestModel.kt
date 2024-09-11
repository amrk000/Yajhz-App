package com.amrk000.yajhaz.model.request

import com.google.gson.annotations.SerializedName

data class SignInRequestModel(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)
