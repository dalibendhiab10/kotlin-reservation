package com.example.mykotlin.api

import com.example.mykotlin.models.EstablishmentData
import com.example.mykotlin.models.LoginRequest
import com.example.mykotlin.models.LoginResponse
import com.example.mykotlin.models.RegisterRequest
import com.example.mykotlin.models.RegisterResponse
import com.example.mykotlin.models.UserProfile
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiInterface {

    @POST("api/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @POST("api/auth/register")
    suspend fun register(@Body registerRequest: RegisterRequest): RegisterResponse

    @PUT("api/user/profile/{id}")
    suspend fun updateUserProfile(
        @Path("id") id: Int,
        @Body userProfile: UserProfile
    ): UserProfile

    @GET("api/establishments")
    suspend fun getEstablishments(): List<EstablishmentData>

    @GET("api/establishments/{id}")
    suspend fun getEstablishmentById(@Path("id") id: Int): EstablishmentData

    
}
