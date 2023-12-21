package com.example.mymarket.data.network

import com.example.mymarket.data.dtos.Cliente
import retrofit2.http.GET
import retrofit2.http.Path

interface ClienteService {
    @GET("clienti/all")
    suspend fun getClienti(
    ): List<Cliente>

    @GET("clienti/one/{email}")
    suspend fun getEmailCliente(
        @Path("email") emailCliente: String
    ): Cliente
}