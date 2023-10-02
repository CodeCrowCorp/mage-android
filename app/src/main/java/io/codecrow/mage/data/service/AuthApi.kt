package io.codecrow.mage.data.service

import io.codecrow.mage.model.AuthUser
import io.codecrow.mage.model.OAuthResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Path

interface AuthApi {
    @GET("auth/me")
    suspend fun getCurrentUser(@HeaderMap headerMap : HashMap<String,String>): Response<AuthUser>

    @GET("auth/{authType}")
    suspend fun getLoginUrl(@Path("authType") authType: String, @HeaderMap headerMap : HashMap<String,String>): Response<OAuthResponse>
}