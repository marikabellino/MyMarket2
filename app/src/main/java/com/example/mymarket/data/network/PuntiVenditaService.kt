package com.example.mymarket.data.network

import com.example.mymarket.data.dtos.PuntiVendita
import retrofit2.http.GET

interface PuntiVenditaService {
    @GET("punto_vendita/all")
    suspend fun getPuntiVendita(
    ) : List<PuntiVendita>
}