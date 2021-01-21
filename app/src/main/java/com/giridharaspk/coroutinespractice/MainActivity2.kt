package com.giridharaspk.coroutinespractice

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity2 : AppCompatActivity() {

    val TAG = MainActivity2::class.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            doNetCall()
            doNetCall2()
            Log.d(TAG, "done") // prints after 4 sec
        }

    }

    suspend fun doNetCall() {
        delay(2000L)
        Log.d(TAG, "call complete 1")
    }

    suspend fun doNetCall2() {
        delay(2000L)
        Log.d(TAG, "call complete 2")
    }

}