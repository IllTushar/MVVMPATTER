package com.example.mvvm.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.CreateUser.Request.RequestCreateUser
import com.example.mvvm.CreateUser.Response.UserResponseClass

import com.example.mvvm.GetUsers.model.ResponseModel
import com.example.mvvm.LoginUser.RequestUser.requestToLogin
import com.example.mvvm.LoginUser.ResponseUser.loginResponse
import com.example.mvvm.repository.userRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val repo: userRepository) : ViewModel() {

    val userMutableLiveData: MutableLiveData<ResponseModel> = MutableLiveData()
    val userData: LiveData<ResponseModel>
        get() = userMutableLiveData


    val error: MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String>
        get() = error


    val createUserMutableLiveData: MutableLiveData<UserResponseClass> = MutableLiveData()
    val createUser: LiveData<UserResponseClass>
        get() = createUserMutableLiveData

    val loginTheUser: MutableLiveData<loginResponse> = MutableLiveData()
    val loginUserLiveData: LiveData<loginResponse>
        get() = loginTheUser

    fun fetchUser() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repo.getUser()
                userMutableLiveData.postValue(result)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun createNewUser(userCreateUser: RequestCreateUser) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repo.createUser(userCreateUser)
                createUserMutableLiveData.postValue(result)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    fun loginTheUser(loginRequest: requestToLogin) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repo.userLogin(loginRequest)
                if (result.isSuccessful && result.body() != null) {
                    if (result.code() == 200) {
                        loginTheUser.postValue(result.body())
                    } else {
                        error.postValue(result.code().toString())
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}