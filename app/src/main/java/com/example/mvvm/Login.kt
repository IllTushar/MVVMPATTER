package com.example.mvvm

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.LoginUser.RequestUser.requestToLogin
import com.example.mvvm.api.RetrofitClient
import com.example.mvvm.repository.userRepository
import com.example.mvvm.viewmodel.UserViewModel
import com.example.mvvm.viewmodel.UserViewModelFactory

class Login : AppCompatActivity() {
    lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)

        val repo = userRepository(RetrofitClient.api)
        val userModelFactory = UserViewModelFactory(repo)

        userViewModel = ViewModelProvider(this, userModelFactory).get(UserViewModel::class.java)

        userViewModel.loginUserLiveData.observe(this, Observer { response ->
            response?.let {
                Log.d("Response", "${it.token.toString()}")
            }
        })
        userViewModel.errorMessage.observe(this, Observer { error ->
            error?.let {
                Log.d("Response", "${it.toString()}")
            }

        })

        val userLogin = requestToLogin("eve.holt@reqres.in", "cityslicka")
        userViewModel.loginTheUser(userLogin)
    }
}