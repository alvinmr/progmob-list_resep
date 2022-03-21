package com.example.listresep.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.listresep.model.RecipeModel
import com.example.listresep.model.ResultModel
import com.example.listresep.retrofit.RetroInstance
import com.example.listresep.retrofit.RetroServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel: ViewModel() {
    var liveDataList: MutableLiveData<MutableList<RecipeModel>> = MutableLiveData()


    fun getLiveDataObserver() : MutableLiveData<MutableList<RecipeModel>>{
        return liveDataList
    }

    fun makeApiCall(page: Int) {

        val retrofit = RetroInstance.getRetrofitInstance()
        val retroService = retrofit.create(RetroServiceInterface::class.java)
        val call = retroService.getRecipes(page)

        call.enqueue(object : Callback<ResultModel>{
            override fun onResponse(call: Call<ResultModel>, response: Response<ResultModel>) {
               liveDataList.postValue(response.body()?.results)
            }

            override fun onFailure(call: Call<ResultModel>, t: Throwable) {
                liveDataList.postValue(null)
                Log.e("response", t.toString())
            }

        })
    }
}