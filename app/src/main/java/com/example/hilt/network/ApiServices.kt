package com.example.hilt.network

import com.example.hilt.model.ActivityResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {
    @GET("activity")
     suspend fun getRepo() : Response<ActivityResponse>
}