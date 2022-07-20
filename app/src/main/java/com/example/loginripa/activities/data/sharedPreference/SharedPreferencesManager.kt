package com.example.loginripa.activities.data.sharedPreference

import android.content.Context
import android.content.SharedPreferences
import com.example.loginripa.activities.data.entity.User
import com.google.gson.Gson

object SharedPreferencesManager {
    private const val PREFERENCES_NAME = "MyPreferences"
    private const val USER_KEY = "USER_KEY"
    private val gson = Gson()

    private fun getPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun saveUser(context: Context, user: User) {
        getPreferences(context)
            .edit()
            .putString(USER_KEY, gson.toJson(user))
            .apply()
    }

    fun getUser(context: Context): User? {
        val userInJson = getPreferences(context)
            .getString(USER_KEY, "")
        return gson.fromJson(userInJson, User::class.java)
    }
}