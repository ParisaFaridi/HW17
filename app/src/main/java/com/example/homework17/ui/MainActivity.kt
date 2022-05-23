package com.example.homework17.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework17.R
import com.example.homework17.ui.homefragment.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val viewModelHome : HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val tv = findViewById<TextView>(R.id.tv_over)
        viewModelHome.getPopularMovie()
        viewModelHome.moviewO.observe(this){
            //tv.text = it
        }

    }
}