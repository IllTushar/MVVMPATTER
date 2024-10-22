package com.example.mvvm

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.api.ApiService
import com.example.mvvm.api.RetrofitClient
import com.example.mvvm.repository.userRepository
import com.example.mvvm.viewmodel.UserViewModel
import com.example.mvvm.viewmodel.UserViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = userRepository(RetrofitClient.api)

        val viewModelFactory = UserViewModelFactory(repository)
        userViewModel = ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)
        userViewModel.userData.observe(this, Observer { response ->
            response?.let {
                for (user in it.data) {
                    Log.d("Response", "${user.email.toString()}")
                }
            }
        })
        userViewModel.errorMessage.observe(this, Observer { error ->
            error?.let {
                Log.d("Error", "${it.toString()}")
            }
        })

        //fetch the data
        userViewModel.fetchUser()
    }
}