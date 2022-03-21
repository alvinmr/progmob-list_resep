package com.example.listresep.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {

    companion object{
        private const val BASE_URL = "https://masak-apa-tomorisakura.vercel.app"

        fun getRetrofitInstance() : Retrofit {
            return Retrofit.Builder()
            	.baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}