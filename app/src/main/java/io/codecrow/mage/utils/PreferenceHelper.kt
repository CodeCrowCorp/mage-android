package io.codecrow.mage.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import io.codecrow.mage.model.AuthUser
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.internal.decodeStringToJsonTree
import kotlinx.serialization.serializer
import javax.inject.Inject

class PreferenceHelper @Inject constructor(@ApplicationContext context: Context) {
    val sharedPref = context.getSharedPreferences("MageAppPrefs", Context.MODE_PRIVATE)

    fun getToken(): String? {
        return sharedPref.getString("token","")
    }
    fun setToken(token: String) {
        sharedPref.edit().putString("token",token).apply()
    }

    fun getUserId(): String? {
        return sharedPref.getString("userId","")
    }
    fun setUserId(userId: String) {
        sharedPref.edit().putString("userId",userId).apply()
    }

    fun saveAuthUser(authUser: AuthUser) {
        val serializer = serializer(AuthUser::class.java)
        val json = Json.encodeToString(serializer,authUser)
        sharedPref.edit().putString("authUser",json).apply()
    }

    fun getAuthUser() : AuthUser? {
        val authUser = sharedPref.getString("authUser","")
        authUser?.let {
            return Json.decodeFromString<AuthUser>(it)
        }
        return null
    }

}