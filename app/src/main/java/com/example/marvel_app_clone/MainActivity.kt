package com.example.marvel_app_clone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marvel_app_clone.databinding.ActivityMainBinding





class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}