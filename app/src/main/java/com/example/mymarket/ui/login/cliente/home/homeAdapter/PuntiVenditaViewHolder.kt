package com.example.mymarket.ui.login.cliente.home.homeAdapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.data.dtos.PuntiVendita
import com.example.mymarket.databinding.ItemsStore2Binding

class PuntiVenditaViewHolder(private val binding: ItemsStore2Binding) :
    RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bindPuntiVenditaCardView(cardView: PuntiVendita) {
        binding.cardTitle3.text = "città: ${cardView.citta}"
        binding.indirizzo3.text = "indirizzo: ${cardView.indirizzo}"
        binding.civico3.text = cardView.civico
        binding.citta3.text = cardView.citta
        binding.cap3.text = cardView.cap.toString()
    }
}