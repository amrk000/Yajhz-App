package com.amrk000.yajhaz.model.response

import com.amrk000.yajhaz.model.UserModel
import com.google.gson.annotations.SerializedName

data class UserResponseModel(
    @SerializedName("data") val user: UserModel?
) : ResponseModel() {

}