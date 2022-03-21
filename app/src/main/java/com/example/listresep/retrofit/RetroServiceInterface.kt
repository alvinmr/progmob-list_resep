package com.example.listresep.retrofit

import com.example.listresep.model.ResultModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetroServiceInterface {

    @GET("/api/recipes/{page}")
    fun getRecipes(@Path("page") page: Int) : Call<ResultModel>
}