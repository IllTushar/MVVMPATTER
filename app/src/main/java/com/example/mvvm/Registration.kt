package com.example.mvvm

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.CreateUser.Request.RequestCreateUser
import com.example.mvvm.api.RetrofitClient
import com.example.mvvm.repository.userRepository
import com.example.mvvm.viewmodel.UserViewModel
import com.example.mvvm.viewmodel.UserViewModelFactory

class Registration : AppCompatActivity() {
    lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val repo = userRepository(RetrofitClient.api)
        val userViewModelFactory = UserViewModelFactory(repo)
        userViewModel = ViewModelProvider(this, userViewModelFactory).get(UserViewModel::class)
        userViewModel.createUser.observe(this, Observer { response ->
            response?.let {
                Toast.makeText(this@Registration, "${it.job}", Toast.LENGTH_LONG).show()
            }
        })
        val userRequest = RequestCreateUser("leader", "morpheus")
        userViewModel.createNewUser(userRequest)
    }
}