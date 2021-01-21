package com.giridharaspk.coroutinespractice

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //lives as long as app lives
        GlobalScope.launch {
//            delay(10000L)
            delay(3000L) // suspend function - can be called inside another suspend fun or in coroutines
            Log.d(TAG, "hello from ${Thread.currentThread().name}")
        }
        Log.d(TAG, "hello from ${Thread.currentThread().name}")

    }

}