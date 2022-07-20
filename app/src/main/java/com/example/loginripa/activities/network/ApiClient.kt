package com.example.loginripa.activities.network

import com.example.loginripa.activities.network.responses.FoodResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

object ApiClient {
    private const val API_URL = "https://api.spoonacular.com/recipes/"
    private const val API_KEY = "10274aa8f2874be997f6d98038021798"

    private var mInterface: AppService

    init {
        val restAdapter =
            Retrofit
                .Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(
                    OkHttpClient.Builder()
                        .connectTimeout(50, TimeUnit.SECONDS)
                        .readTimeout(50, TimeUnit.SECONDS)
                        .addInterceptor(
                            HttpLoggingInterceptor()
                                .setLevel(HttpLoggingInterceptor.Level.BODY)
                        )
                        .build()
                )
                .build()

        mInterface = restAdapter.create(AppService::class.java)
    }

    fun getServiceClient() = mInterface

    interface AppService {
        @Headers("x-api-key: $API_KEY")
        @GET("random")
        fun getFoods(@Query("number") resultsQuantity: Int): Call<FoodResponse>
    }
}