package com.giridharaspk.coroutinespractice

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.giridharaspk.coroutinespractice.databinding.ActivityMain7Binding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity7 : AppCompatActivity() {
    private lateinit var binding: ActivityMain7Binding
    private val TAG: String? = MainActivity7::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain7Binding.inflate(layoutInflater)
        setContentView(binding.root)
        var count = 0
        binding.button.setOnClickListener {
            /*GlobalScope.launch {
                while (true) {
                    delay(1000L)
                    count++
                    Log.e(TAG, "running $count")
                }
            }*/
            lifecycleScope.launch {
                while (true) {
                    delay(1000L)
                    count++
                    Log.e(TAG, "running $count")
                }
            }
            /*viewModelScope.launch { // to be written in viewmodel
                while (true) {
                    delay(1000L)
                    count++
                    Log.e(TAG, "running $count")
                }
            }*/
            GlobalScope.launch {
                delay(5000L)
                Intent(this@MainActivity7, SecondActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }

    }
}