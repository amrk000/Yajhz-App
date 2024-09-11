package com.amrk000.yajhaz.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import com.amrk000.yajhaz.model.UserModel
import com.google.gson.Gson
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized


class SharedPrefManager private constructor (context: Context) {
    private var sharedPreferences: SharedPreferences

    // Keys
    private val SIGNED_IN = "signedIn"
    private val SESSION_TOKEN = "token"
    private val USER_DATA = "userData"
    private val LANGUAGE = "language"

    //Languages
    private val LANGUAGE_ARABIC = "ar"
    private val LANGUAGE_ENGLISH = "en"

    init {
        sharedPreferences = context.getSharedPreferences("prefs", MODE_PRIVATE)
    }

    companion object {
        private lateinit var instance: SharedPrefManager

        fun get(context: Context): SharedPrefManager {
            if (!this::instance.isInitialized) instance = SharedPrefManager(context)
            return instance
        }
    }

    var userSignedIn: Boolean
        get() = sharedPreferences.getBoolean(SIGNED_IN, false)
        set(signedIn) {
            sharedPreferences.edit()
                .putBoolean(SIGNED_IN, signedIn)
                .apply()
        }

    var userData: UserModel?
        get() = Gson().fromJson(sharedPreferences.getString(USER_DATA, null), UserModel::class.java)
        set(userData) {
            sharedPreferences.edit()
                .putString(USER_DATA, Gson().toJson(userData))
                .apply()
        }

    var sessionToken: String?
        get() = sharedPreferences.getString(SESSION_TOKEN, null)
        set(sessionToken) {
            sharedPreferences.edit()
                .putString(SESSION_TOKEN, sessionToken)
                .apply()
        }

    var currentLang: String
        get() = sharedPreferences.getString(LANGUAGE, LANGUAGE_ENGLISH).toString()
        set(lang) {
        sharedPreferences.edit()
            .putString(LANGUAGE, lang)
            .apply()
        }
}