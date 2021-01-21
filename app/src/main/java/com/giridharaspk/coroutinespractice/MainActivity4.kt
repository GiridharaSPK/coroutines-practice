package com.giridharaspk.coroutinespractice

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MainActivity4 : AppCompatActivity() {

    val TAG = MainActivity4::class.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

/*        GlobalScope.launch(Dispatchers.Main) {
            delay(100L)
        }*/

        Log.d(TAG, "before runBlocking ${Thread.currentThread().name}")
        //in coroutine scope
        //blocks UI updates
        //useful in junit to access suspend functions from test functions

        runBlocking {
            Log.d(TAG, "start runBlocking ${Thread.currentThread().name}")
            withContext(Dispatchers.IO) {
                delay(3000L)
                Log.d(TAG, "done - from :  ${Thread.currentThread().name}")
            }
            withContext(Dispatchers.IO) {
                delay(3000L)
                Log.d(TAG, "done - from :  ${Thread.currentThread().name}")
            }
            Log.d(TAG, "==== ${Thread.currentThread().name}")
            delay(5000L)
            Log.d(TAG, "end runBlocking ${Thread.currentThread().name}")
        }
        Log.d(TAG, "after runBlocking ${Thread.currentThread().name}")

    }

}