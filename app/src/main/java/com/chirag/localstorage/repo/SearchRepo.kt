package com.chirag.localstorage.repo

import com.chirag.localstorage.extension.logv
import com.chirag.localstorage.network.RetrofitAPI
import com.chirag.localstorage.network.RetrofitClient
import com.chirag.localstorage.entity.GitResponse
import com.chirag.localstorage.modules.search.storage.SearchResultDb
import com.google.gson.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchRepo {

    suspend fun searchGitHubRepo(query:String) =
        withContext(Dispatchers.IO){
            val call = RetrofitClient.getClient()
                .create(RetrofitAPI::class.java)
                .getPosts(query)
            call.enqueue(object : Callback<JsonObject> {
                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    logv("realm====> ${t.localizedMessage}}")
                }

                override  fun onResponse(
                    call: Call<JsonObject>,
                    response: Response<JsonObject>
                ) {
                    logv("realm====> ${response.code()}}")
                    if (response.isSuccessful){
                        when (response.code()){
                            200->{
                                val result =Gson().fromJson(response.body(), GitResponse::class.java)
                                result.items?.let {
                                    SearchResultDb().storePostsListingData(it)

                                }
                            }

                        }
                    }

                }

            })
        }

}

