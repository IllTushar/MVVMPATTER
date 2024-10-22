package com.example.mvvm.api

import com.example.mvvm.CreateUser.Request.RequestCreateUser
import com.example.mvvm.CreateUser.Response.UserResponseClass
import com.example.mvvm.GetUsers.model.ResponseModel
import com.example.mvvm.LoginUser.RequestUser.requestToLogin
import com.example.mvvm.LoginUser.ResponseUser.loginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("api/users?page=2")
    suspend fun getUserData(): ResponseModel

    @POST("api/users")
    suspend fun createUser(@Body requestCreateUser: RequestCreateUser): UserResponseClass

    @POST("api/login")
    suspend fun loginUser(@Body loginRequest: requestToLogin): Response<loginResponse>
}