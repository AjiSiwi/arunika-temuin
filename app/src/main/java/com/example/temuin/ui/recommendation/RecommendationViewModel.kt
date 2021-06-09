package com.example.temuin.ui.recommendation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.temuin.api.RetrofitClient
import com.example.temuin.data.CommunityEntity
import com.example.temuin.data.model.CommunityRequest
import com.example.temuin.data.model.CommunityResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendationViewModel : ViewModel(){

    val listCommunity = MutableLiveData<ArrayList<CommunityEntity>>()

    fun setCommunity(type: String){
        val communityType = CommunityRequest()
        communityType.input = type
            RetrofitClient.apiInstance
                .getCommunity(communityType)
                .enqueue(object : Callback<CommunityResponse> {
                    override fun onResponse(
                        call: Call<CommunityResponse>,
                        response: Response<CommunityResponse>
                    ) {
                        if(response.isSuccessful){
                            listCommunity.postValue(response.body()?.communities)
                        }
                    }

                    override fun onFailure(call: Call<CommunityResponse>, t: Throwable) {
                        Log.d("Failure", t.message.toString())
                    }

                })
    }

    fun getCommunity(): LiveData<ArrayList<CommunityEntity>>{
        return listCommunity
    }
}