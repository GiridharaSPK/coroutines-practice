package com.giridharaspk.coroutinespractice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class MainActivity9 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        launch{}, async{}, runBlocking{},  - every coroutine has its own coroutine scope instance attached
        myFun()
        // like coroutine scope, every coroutine has its own coroutine context -  but it is inherited from parent coroutine
        // coroutine context has 2 major components - Job and dispatchers
        //dispatcher - which thread to execute coroutine on
        //job - launch returns job obj
        //coroutine name - assign a name to it
        myFun2()
    }

    fun myFun() = runBlocking {
        //this - corutine scope object
        println("runblocking : $this") //blocking coroutine
        launch {
            println("launch $this")//(non blocking) Standalone coroutine

            launch {
                print("child launch $this")// different hashcode
            }
        }

        async {
            print("async coroutine $this")//deferred coroutine
        }
    }

    fun myFun2() = runBlocking {
//        coroutineContext
        //main thread
        launch {
            //inherits coroutine context (dispatchers)
            println("C1 : ${Thread.currentThread().name}")//main
        }

        //Dispatchers.Default - similar to GlobalScope.launch{} - coroutine is created in application level
        //gets its own context at global level
        //after suspending function - the execution thread might change depending on the availability
        launch(Dispatchers.Default) {
            println("C2 : ${Thread.currentThread().name}")//thread T1
            delay(2000)
            println("C2 after delay : ${Thread.currentThread().name}")//thread may change
        }

        launch(Dispatchers.Unconfined) {
            println("C3 : ${Thread.currentThread().name}")//main thread (inherited)
            delay(1800)
            println("C3 after delay: ${Thread.currentThread().name}")//thread changes
        }

        //but in case of launch{} without any parameter, the thread doesnt change after suspend function
        //main thread
        //called confined dispatcher
        launch {
            //inherits coroutine context (dispatchers)
            println("C4 : ${Thread.currentThread().name}")//main
            delay(1600)
            println("C4 after delay: ${Thread.currentThread().name}")//main
        }

        //using coroutine context property to flow context from parent to child
        launch(coroutineContext){
            //inherits coroutine context (from runBlocking)
            println("C5 : ${Thread.currentThread().name}")//main
            delay(1600)
            println("C5 after delay: ${Thread.currentThread().name}")//main
        }

        launch(Dispatchers.Unconfined) {
            println("C6 : ${Thread.currentThread().name}")//main thread (inherited)
            delay(1500)
            println("C6 after delay: ${Thread.currentThread().name}")//thread changes T1

            //using coroutine context property to flow context from parent to child
            launch(coroutineContext){
                //inherits coroutine context (from runBlocking)
                println("C7 : ${Thread.currentThread().name}")//T1
                delay(1000)
                println("C7 after delay: ${Thread.currentThread().name}")//T1
            }

        }

    }

}