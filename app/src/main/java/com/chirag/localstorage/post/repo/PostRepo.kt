package com.chirag.localstorage.post.repo

import com.chirag.localstorage.network.RetrofitAPI
import com.chirag.localstorage.network.RetrofitClient
import com.chirag.localstorage.network.myGson
import com.chirag.localstorage.post.entity.Post
import com.chirag.localstorage.post.storage.PostRealmOperation
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostRepo {

    fun getPosts() {
      val call = RetrofitClient.getClient()
            .create(RetrofitAPI::class.java)
            .getPosts()
        call.enqueue(object : Callback<JsonArray> {
            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<JsonArray>,
                response: Response<JsonArray>
            ) {
                if (response.isSuccessful){
                    when (response.code()){

                        200->{
                            val typeToken = object : TypeToken<ArrayList<Post>>() {}
                            val list = myGson().fromJson<ArrayList<Post>>(
                                (response.body()),
                                typeToken.type
                            )
                            PostRealmOperation().storePostsListingData(list)
                        }
                    }
                }

            }

        })
    }

}

