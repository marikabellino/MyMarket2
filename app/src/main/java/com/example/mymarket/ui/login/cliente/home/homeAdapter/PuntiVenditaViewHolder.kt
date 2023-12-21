package com.example.mymarket.ui.login.cliente.home.homeAdapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.data.dtos.PuntiVendita
import com.example.mymarket.databinding.ItemsPuntiVenditaBinding

class PuntiVenditaViewHolder(private val binding: ItemsPuntiVenditaBinding) :
    RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bindPuntiVenditaCardView(cardView: PuntiVendita) {
        binding.cittaPuntoVendita.text = "città: ${cardView.citta}"
        binding.indirizzoPuntoVendita.text = "indirizzo: ${cardView.indirizzo}"
        binding.civicoPuntoVendita.text = cardView.civico
        binding.capPuntoVendita.text = cardView.cap.toString()
    }
}