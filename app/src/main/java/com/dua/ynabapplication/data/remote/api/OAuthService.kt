package com.dua.ynabapplication.data.remote.api

import com.dua.ynabapplication.repository.models.auth.AuthModel
import com.dua.ynabapplication.utils.Constants.Companion.OAUTH_API_ENDPOINT
import retrofit2.Response
import retrofit2.http.*


interface OAuthService {

    @FormUrlEncoded
    @POST("$OAUTH_API_ENDPOINT/token")
    suspend fun getAccessToken(@Field("code") code: String, @Field("client_id") clientId: String?,
                       @Field("client_secret") clientSecret: String?, @Field("redirect_uri") redirectUri: String,
                       @Field("grant_type") grantType: String? = "authorization_code"): Response<AuthModel>

    @FormUrlEncoded
    @POST("$OAUTH_API_ENDPOINT/token")
    suspend fun getRefreshToken(@Field("grant_type") grantType: String?,
                        @Field("refresh_token") refreshToken: String?,
                        @Field("client_id") clientId: String?, @Field("client_secret") clientSecret: String?): Response<AuthModel>

}