package com.example.mymarket.data.dtos

data class Cliente(
    val admin: Boolean,
    val cognome: String,
    val email: String,
    val nome: String,
    val ordine: List<Ordini>,
    val password: String
)