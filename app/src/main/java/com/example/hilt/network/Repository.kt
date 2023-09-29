package com.example.hilt.network

import com.example.hilt.model.ActivityResponse
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject


class Repository @Inject constructor(private val apiServices: ApiServices) {

    suspend fun getRepo(): Response<ActivityResponse> {
        return apiServices.getRepo()
    }
}