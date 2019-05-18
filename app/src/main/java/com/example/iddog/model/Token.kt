package com.example.iddog.model

import android.content.Context
import android.content.SharedPreferences

class Token(val context: Context) {

    private val PREFS_NAME = "ID_DOG"
    private val KEY = "LOGIN_TOKEN"
    private val sharedPref: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun create(tokenValue: String){
        sharedPref.edit().apply{
            putString(KEY, tokenValue)
            apply()
        }
    }

    fun request(): String? {
        return sharedPref.getString(KEY, null)
    }

    fun clear() {
        sharedPref.edit().apply{
            clear()
            apply()
        }
    }

    fun hasToken(): Boolean {
        return request()?.isNotEmpty() ?: false
    }

}