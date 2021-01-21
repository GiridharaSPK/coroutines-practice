package com.giridharaspk.coroutinespractice

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class MainActivity6 : AppCompatActivity() {

    val TAG = MainActivity6::class.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //lives as long as app lives
        /*GlobalScope.launch(Dispatchers.IO) {
            val time = measureTimeMillis {
                val ans1 = networkCall1()
                val ans2 = networkCall2()
                Log.d(TAG, "ans1 = $ans1")
                Log.d(TAG, "ans2 = $ans2")
            }
            Log.d(TAG, "time = $time") // executed in 4 sec
        }*/

        /*GlobalScope.launch(Dispatchers.IO) {
            val time = measureTimeMillis {
                var ans1: String? = null
                var ans2: String? = null
                val job1 = launch {
                    ans1 = networkCall1()
                }
                val job2 = launch {
                    ans2 = networkCall2()
                }
//                job1.join()
//                job2.join()
                Log.d(TAG, "ans1 = $ans1")
                Log.d(TAG, "ans2 = $ans2")
            }
            Log.d(TAG, "time = $time") //executed in 4 sec
        }*/

        /*GlobalScope.launch(Dispatchers.IO) {
            val time = measureTimeMillis {
                var ans1: String? = null
                var ans2: String? = null
                val job1 = launch {
                    ans1 = networkCall1()
                }
                val job2 = launch {
                    ans2 = networkCall2()
                }
                job1.join()
                job2.join()
                Log.d(TAG, "ans1 = $ans1")
                Log.d(TAG, "ans2 = $ans2")
            }
            Log.d(TAG, "time = $time") //executed in 2 sec
        }*/

        GlobalScope.launch(Dispatchers.IO) {
            val time = measureTimeMillis {
                val ans1 = async { networkCall1() }
                val ans2 = async { networkCall2() }
                Log.d(TAG, "ans1 = ${ans1.await()}")
                Log.d(TAG, "ans2 = ${ans2.await()}")
            }
            Log.d(TAG, "time = $time") //executed in 2 sec
        }

    }

    suspend fun networkCall1(): String {
        delay(2000L)
        return "ANSWER 1"
    }

    suspend fun networkCall2(): String {
        delay(2000L)
        return "ANSWER 2"
    }

}