package com.example.mvvm.repository

import com.example.mvvm.CreateUser.Request.RequestCreateUser
import com.example.mvvm.CreateUser.Response.UserResponseClass
import com.example.mvvm.api.ApiService
import com.example.mvvm.GetUsers.model.ResponseModel
import com.example.mvvm.LoginUser.RequestUser.requestToLogin
import com.example.mvvm.LoginUser.ResponseUser.loginResponse
import retrofit2.Response

class userRepository(private val apiService: ApiService) {
    suspend fun getUser(): ResponseModel {
        return apiService.getUserData()
    }

    suspend fun createUser(requestCreateUser: RequestCreateUser): UserResponseClass {
        return apiService.createUser(requestCreateUser)
    }

    suspend fun userLogin(loginRequest: requestToLogin):Response<loginResponse> {
        return apiService.loginUser(loginRequest)
    }
}