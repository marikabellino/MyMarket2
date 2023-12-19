package com.example.mymarket.data.network

//import com.example.mymarket.data.dtos.Cliente
import com.example.mymarket.model.User
import retrofit2.http.GET
import retrofit2.http.Query

interface ClienteService {
    @GET("clienti/all")
    suspend fun getDetailsCliente() : List<User>
}