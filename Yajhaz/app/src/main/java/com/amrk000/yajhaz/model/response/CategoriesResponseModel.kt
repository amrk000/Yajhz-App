package com.amrk000.yajhaz.model.response

import com.amrk000.yajhaz.model.CategoryModel
import com.google.gson.annotations.SerializedName

data class CategoriesResponseModel(
    @SerializedName("data") val categories: ArrayList<CategoryModel>?
) : ResponseModel() {

}