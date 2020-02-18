package com.chirag.localstorage.post.repo

import com.chirag.localstorage.network.RetrofitAPI
import com.chirag.localstorage.network.RetrofitClient
import com.chirag.localstorage.network.myGson
import com.chirag.localstorage.post.entity.Post
import com.chirag.localstorage.post.storage.PostRealmOperation
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Handler

class PostRepo {

         suspend fun getPosts(result: (ArrayList<Post>) -> Unit = { }) =
             withContext(Dispatchers.IO){
            val call = RetrofitClient.getClient()
                    .create(RetrofitAPI::class.java)
                    .getPosts()
                call.enqueue(object : Callback<JsonArray> {
                    override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                    }

                    override  fun onResponse(
                        call: Call<JsonArray>,
                        response: Response<JsonArray>
                    ) {
                        if (response.isSuccessful){
                            when (response.code()){

                                200->{
                                    val typeToken = object : TypeToken<ArrayList<Post>>() {}
                                    val postArrayList = myGson().fromJson<ArrayList<Post>>(
                                        (response.body()),
                                        typeToken.type
                                    )

                                    result(postArrayList)
                                }
                            }
                        }

                    }

                })
        }

}

