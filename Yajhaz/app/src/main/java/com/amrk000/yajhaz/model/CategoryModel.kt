package com.amrk000.yajhaz.model

import com.google.gson.annotations.SerializedName

data class CategoryModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String,
    @SerializedName("active") val active: Int
)
