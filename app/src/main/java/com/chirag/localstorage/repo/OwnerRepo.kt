package com.chirag.localstorage.repo

import com.chirag.localstorage.extension.logv
import com.chirag.localstorage.network.RetrofitAPI
import com.chirag.localstorage.network.RetrofitClient
import com.chirag.localstorage.entity.GitResponse
import com.chirag.localstorage.entity.Owner
import com.chirag.localstorage.entity.Post
import com.chirag.localstorage.modules.owner.storage.OwnerDB
import com.chirag.localstorage.modules.search.storage.SearchResultDb
import com.chirag.localstorage.network.myGson
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OwnerRepo {

    suspend fun getContributers(repo:String,login:String,result: (ArrayList<Owner>) -> Unit = { }) =
        withContext(Dispatchers.IO){
            val call = RetrofitClient.getClient()
                .create(RetrofitAPI::class.java)
                .getRepoDetail(repo = repo,login = login)
            call.enqueue(object : Callback<JsonArray> {
                override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                    logv("realm====> ${t.localizedMessage}}")
                }
                override  fun onResponse(
                    call: Call<JsonArray>,
                    response: Response<JsonArray>
                ) {
                    logv("realm====> ${response.code()}}")
                    if (response.isSuccessful){
                        when (response.code()){
                            200->{
                                val typeToken = object : TypeToken<ArrayList<Owner>>() {}
                                val contributers = myGson().fromJson<ArrayList<Owner>>(
                                    (response.body()),
                                    typeToken.type
                                )
                                result(contributers)
                            }

                        }
                    }

                }

            })
        }

}

