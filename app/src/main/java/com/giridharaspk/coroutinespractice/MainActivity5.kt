package com.giridharaspk.coroutinespractice

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class MainActivity5 : AppCompatActivity() {

    val TAG = MainActivity5::class.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val job1 = GlobalScope.launch(Dispatchers.Default) {
            repeat(5) {
                Log.d(TAG, "inside coroutine")
                delay(1000L)
            }
        }
        /*runBlocking {
            job.join() // blocks main until the above coroutine is finished
            Log.d(TAG, "Main Thread is continuing")
        }*/
        runBlocking {
            delay(2000L)
            job1.cancel() // blocks main until the above coroutine is finished
            Log.e(TAG, "canceled job1")
        }
        Log.e(TAG, "==========")

        val job2 = GlobalScope.launch(Dispatchers.Default) {
            for (i in 30..50) {
                if (isActive) // to check if job is alive - without this check, busy coroutines dont check if job is cancelled or not
                    Log.d(TAG, "$i : ${fib(i)}")
            }
        }

        runBlocking {
            delay(500L)
            job2.cancel()
            Log.e(TAG, "canceled job2")
        }

        Log.e(TAG, "\n===========\n=========")

        GlobalScope.launch(Dispatchers.Default) {
            withTimeout(400L) {
                for (i in 30..50) {
                    if(isActive)//required
                    Log.d(TAG, "$i : ${fib(i)}")
                }
            }
        }
    }

    fun fib(i: Int): Int {
        return if (i < 2)
            i
        else
            fib(i - 1) + fib(i - 2)
    }

}