package com.amrk000.yajhaz.model

import com.amrk000.yajhaz.model.response.ResponseModel
import com.google.gson.annotations.SerializedName

data class SellerModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("image") val image: String,
    @SerializedName("logo") val logo: String,
    @SerializedName("description") val description: String,
    @SerializedName("distance") val distance: String,
    @SerializedName("type") val type: Int,
    @SerializedName("status") val status: Int,
    @SerializedName("lat") val lat: String,
    @SerializedName("lng") val lng: String,
    @SerializedName("address") val address: String,
    @SerializedName("appointments") val appointments: String,
    @SerializedName("trending") val trending: Int,
    @SerializedName("popular") val popular: Int,
    @SerializedName("rate") val rate: String,
    @SerializedName("is_favorite") val isFavorite: Boolean,
    @SerializedName("categories") val categories: List<CategoryModel>?,
    @SerializedName("product_categories") val productCategories: List<CategoryModel>?,
    @SerializedName("token") val token: String,
    @SerializedName("information") val information: Information?
) : ResponseModel(){

    data class Information(
        @SerializedName("id") val id: Int,
        @SerializedName("identity_number") val identityNumber: String,
        @SerializedName("tax_record") val taxRecord: String,
        @SerializedName("activity") val activity: String?,
        @SerializedName("nationality") val nationality: String,
        @SerializedName("vehicle_image") val vehicleImage: String,
        @SerializedName("vehicle_tablet_image") val vehicleTabletImage: String,
        @SerializedName("vehicle_registration") val vehicleRegistration: String,
        @SerializedName("driving_image") val drivingImage: String
    )

}