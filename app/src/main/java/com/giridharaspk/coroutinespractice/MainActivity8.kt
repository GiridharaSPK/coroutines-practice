package com.giridharaspk.coroutinespractice

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity8 : AppCompatActivity() {
    val BASE_URL = "https://jsonplaceholder.typicode.com/"
    val TAG = MainActivity8::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(ApiInterface::class.java)


        /*api.getComments().enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        for (comment in it) {
                            Log.i(TAG, comment.toString())
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Log.e(TAG, "onFailure : $t")
            }
        })*/
/*        GlobalScope.launch(Dispatchers.IO) {
            api.getComments().await().let {
                for (comment in it) {
                    Log.i(TAG, comment.toString())
                }
            }
        }*/

        /*GlobalScope.launch(Dispatchers.IO) {
            val response = api.getComments().awaitResponse()
            if (response.isSuccessful) {
                if (response.body() != null) {
                    response.body()?.let {
                        for (comment in it) {
                            Log.i(TAG, comment.toString())
                        }
                    }
                } else {
                    Log.e(TAG, "response body null")
                }
            } else {
                Log.e(TAG, "response failed")
            }
        }*/


        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getComments2()
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        response.body()?.let {
                            for (comment in it) {
                                Log.i(TAG, comment.toString())
                            }
                        }
                    } else {
                        Log.e(TAG, "response body null")
                    }
                } else {
                    Log.e(TAG, "response failed")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Exception", e)
            }
        }

    }


}