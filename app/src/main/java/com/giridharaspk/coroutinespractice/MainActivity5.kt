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
                //yield() //similar to delay but very small wait time
            }
        }
        /*runBlocking {
            job.join() // blocks main until the above coroutine is finished
            Log.d(TAG, "Main Thread is continuing")
        }*/
        runBlocking {
            delay(2000L)
            job1.cancel() // blocks main until the above coroutine is finished
//            job1.cancelAndJoin() //job cancel works only if the job is cooperative - else need to use cancelAndJoin()
            Log.e(TAG, "canceled job1")
        }
        Log.e(TAG, "==========")
        val job2 = GlobalScope.launch(Dispatchers.Default) {
            try {
                for (i in 30..50) {
                    if (isActive) {// to check if job is alive - without this check, busy coroutines dont check if job is cancelled or not - for non cooperative jobs
                        Log.d(TAG, "$i : ${fib(i)}")
                        yield()
//                }else{
//                    return@launch
                    }
                }
            } catch (e: CancellationException) {
                Log.e(
                    TAG,
                    "Cancellation exception thrown by suspending functions when cancelled",
                    e
                )
            } finally {
                //cannot using suspending functions here directly as the coroutine is already cancelled
                withContext(NonCancellable) {
                    delay(100)
                    Log.i(TAG, "finally - creates coroutine in another context")
                }
            }
        }

        runBlocking {
            delay(500L)
//            job2.cancel()
//            job2.cancel("")
            job2.cancel(CancellationException("my crash message"))
            Log.e(TAG, "canceled job2")
        }

        Log.e(TAG, "\n===========\n=========")

        GlobalScope.launch(Dispatchers.Default) {
            withTimeout(400L) { //can throw exception if job is cancelled -> withTimeoutOrNull dont throw and also returns any value like lambda
                for (i in 30..50) {
                    if (isActive) {//required
                        Log.d(TAG, "$i : ${fib(i)}")
                    } else {
                        return@withTimeout
                    }
                }
            }

            //lazy - //gets executed only required i.e if a.await() is called
            val a = async(start = CoroutineStart.LAZY) { fib(2) }
//            a.await()
        }
    }

    fun fib(i: Int): Int {
        return if (i < 2)
            i
        else
            fib(i - 1) + fib(i - 2)
    }

}