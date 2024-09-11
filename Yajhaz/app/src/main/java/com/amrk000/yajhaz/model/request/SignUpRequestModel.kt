package com.amrk000.yajhaz.model.request

import com.google.gson.annotations.SerializedName

data class SignUpRequestModel(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("name") val name: String
)
