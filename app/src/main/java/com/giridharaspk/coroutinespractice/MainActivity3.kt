package com.giridharaspk.coroutinespractice

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.giridharaspk.coroutinespractice.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity3 : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    val TAG = MainActivity3::class.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //Dispatcher.Main  - do operations on main thread from coroutine
        //Dispatcher.IO  - do operations on IO operations - network call, db update etc

        GlobalScope.launch {
            Log.d(TAG, "1) thread :${Thread.currentThread().name}")
            val ans = doNetCall()
            withContext(Dispatchers.Main) {
                binding?.tvText?.text = ans
                Log.d(TAG, "2) thread :${Thread.currentThread().name}")
            }
            Log.d(TAG, "3) thread :${Thread.currentThread().name}")
        }

        //Dispatcher.Default  - do complex and long running operations
        //Dispatcher.unconfined  - do operations - after suspend function it will not stay in same thread
        /* GlobalScope.launch(Dispatchers.Unconfined) {
             Log.d(TAG, "thread : ${Thread.currentThread().name}")
             delay(1000L)
             Log.d(TAG, "thread : ${Thread.currentThread().name}")
         }*/

//        start a new thread a run coroutine in it
/*        GlobalScope.launch { newSingleThreadContext("MyThread") }*/


    }

    suspend fun doNetCall(): String {
        delay(2000L)
        Log.d(TAG, "doNetCall done")
        return "message"
    }

}