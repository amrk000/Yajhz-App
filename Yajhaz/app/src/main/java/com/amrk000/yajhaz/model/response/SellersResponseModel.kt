package com.amrk000.yajhaz.model.response

import com.amrk000.yajhaz.model.SellerModel
import com.google.gson.annotations.SerializedName

data class SellersResponseModel(
    @SerializedName("data") val sellers: ArrayList<SellerModel>?
) : ResponseModel() {

}