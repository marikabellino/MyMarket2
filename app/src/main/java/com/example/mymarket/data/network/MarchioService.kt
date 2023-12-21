package com.example.mymarket.data.network

import com.example.mymarket.data.dtos.Marchio
import retrofit2.http.GET

interface MarchioService {
    @GET("marchio/all")
    suspend fun getDetailsMarchio(

    ) : List<Marchio>
}