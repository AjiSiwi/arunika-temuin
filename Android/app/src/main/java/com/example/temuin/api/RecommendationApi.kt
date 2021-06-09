package com.example.temuin.api

import com.example.temuin.data.model.CommunityRequest
import com.example.temuin.data.model.CommunityResponse
import com.example.temuin.data.model.SurveyRequest
import com.example.temuin.data.model.SurveyResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RecommendationApi {
    @POST("predict-community-2")
    fun predictCommunity(@Body req: SurveyRequest) : Call<SurveyResponse>

    @POST("get-communities-2")
    fun getCommunity(@Body req: CommunityRequest) : Call<CommunityResponse>
}