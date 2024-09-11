package com.amrk000.yajhaz.model.response

import com.google.gson.annotations.SerializedName

open class ResponseModel {
    companion object {
        const val SUCCESSFUL_OPERATION = 200 //Successful: Data Process, Data Update, Data Retrieval
        const val SUCCESSFUL_CREATION = 201 //Successful: Data Creation
        const val SUCCESSFUL_DELETED = 204 //Successful: Data Deleted

        const val FAILED_INVALID_DATA = 400 //Failed: Expired Token, Data Error
        const val FAILED_AUTH = 401 //Failed: Wrong Password, Not Authorized User
        const val FAILED_NOT_FOUND = 404 //Failed: Token Not Found, User Not Found
        const val FAILED_DATA_CONFLICT = 409 //Failed: Data Already Exists

        const val FAILED_SERVER_ERROR = 500 //Failed: Server Error
        const val FAILED_SERVER_DOWN = 503 //Failed: Server Down / Service Not Available
        const val FAILED_REQUEST_FAILURE = 504 //Failed: Request Error
    }

    @SerializedName("message")
    lateinit var message: String

    @SerializedName("success")
    var success: Boolean = false

    @SerializedName("response_code")
    var code: Int = 0

}