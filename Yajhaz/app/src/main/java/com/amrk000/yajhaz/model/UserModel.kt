package com.amrk000.yajhaz.model

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("image") val image: String,
    @SerializedName("type") val type: Int,
    @SerializedName("status") val status: Int,
    @SerializedName("balance") val balance: String,
    @SerializedName("addresses") val addresses: List<AddressModel>,
    @SerializedName("token") val token: String
) {

    data class AddressModel(
        @SerializedName("id") val id: Int,
        @SerializedName("lat") val lat: String,
        @SerializedName("lng") val lng: String,
        @SerializedName("address") val address: String?,
        @SerializedName("street") val street: String,
        @SerializedName("building") val building: String,
        @SerializedName("apartment") val apartment: String,
        @SerializedName("floor") val floor: String?,
        @SerializedName("name") val name: String?
    )

}