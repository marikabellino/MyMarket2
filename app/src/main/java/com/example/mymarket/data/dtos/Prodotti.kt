package com.example.mymarket.data.dtos

data class Prodotti(
    val id: Int,
    val iva: Int,
    val nome: String,
    val origine: String,
    val prezzo: Double,
    val quantita: Int,
    val scadenza: String,
    val tipologia: String
)