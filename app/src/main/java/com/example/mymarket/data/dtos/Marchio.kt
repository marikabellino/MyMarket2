package com.example.mymarket.data.dtos

data class Marchio(
    val id: Int,
    val nome: String,
    val puntiVendita: List<PuntiVendita>
)
