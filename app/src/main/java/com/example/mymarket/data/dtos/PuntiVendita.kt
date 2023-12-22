package com.example.mymarket.data.dtos

data class PuntiVendita(
    val cap: Int,
    val citta: String,
    val civico: String,
    val id: Int,
    val indirizzo: String,
    val prodotti: List<Prodotti>
)
