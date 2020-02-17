package com.chirag.localstorage.network

import com.chirag.localstorage.BuildConfig
import com.google.gson.*
import com.google.gson.internal.bind.TypeAdapters
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.io.IOException
import java.util.concurrent.TimeUnit

object RetrofitClient {
    fun getClient(
    ): Retrofit {
        val client = OkHttpClient.Builder().addInterceptor(Interceptor { chain ->

            var request = chain.request().newBuilder().build()
            val response = chain.proceed(request)
            response
        })
            .connectTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }
}

object HMap {
    const val post = "post"
    const val json = "json"
    const val ACTIVE = "ACTIVE"

}

interface RetrofitAPI {
    @GET("$post")
    fun getPosts(): Call<JsonArray>

    companion object {
        const val post = "posts"

    }
}


private val UNRELIABLE_INTEGER: TypeAdapter<Number> = object : TypeAdapter<Number>() {
    @Throws(IOException::class)
    override fun read(`in`: JsonReader): Number? {
        val jsonToken = `in`.peek()
        when (jsonToken) {
            TypeAdapters.NUMBER, TypeAdapters.STRING, TypeAdapters.DOUBLE, JsonToken.STRING, JsonToken.NUMBER -> {
                val s = `in`.nextString()
                try {
                    return Integer.parseInt(s)
                } catch (ignored: NumberFormatException) {
                }

                try {
                    return java.lang.Double.parseDouble(s).toInt()
                } catch (ignored: NumberFormatException) {
                }

                return null
            }
            JSONObject.NULL -> {
                `in`.nextNull()
                return null
            }
            TypeAdapters.BOOLEAN -> {
                `in`.nextBoolean()
                return null
            }
            else -> throw JsonSyntaxException("Expecting number, got: $jsonToken")
        }
    }

    @Throws(IOException::class)
    override fun write(out: JsonWriter, value: Number) {
        out.value(value)
    }
}

private val UNRELIABLE_INTEGER_FACTORY =
    TypeAdapters.newFactory(Int::class.javaPrimitiveType, Int::class.java, UNRELIABLE_INTEGER)


fun myGson(): Gson {
    return GsonBuilder().registerTypeAdapterFactory(UNRELIABLE_INTEGER_FACTORY).create()!!
}



