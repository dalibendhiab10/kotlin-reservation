package com.example.mykotlin.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.mykotlin.api.ApiService

object Constants {
    const val PREF_NAME = "REZERV"
    private const val TOKEN_KEY = "token"
    private const val ROLE_KEY = "role"
    const val BASE_URL = "http://20.20.22.36:8080"

    var ESTABLISHMENT=null;
    // Default values
    var TOKEN: String = ""
        private set
    var ROLE: String = "ADMIN"  // Default role
        private set

    fun initialize(context: Context) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        TOKEN = prefs.getString(TOKEN_KEY, "") ?: ""
        ROLE = prefs.getString(ROLE_KEY, "ADMIN") ?: "ADMIN"
    }

    fun saveToken(context: Context, token: String) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(TOKEN_KEY, token).apply()
        TOKEN = token  // Update the in-memory value
    }

    fun saveRole(context: Context, role: String) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(ROLE_KEY, role).apply()
        ROLE = role
    }
    fun getAuthToken(context: Context): String? {
        val prefs: SharedPreferences? = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs?.getString("auth_token", null)
    }

}
