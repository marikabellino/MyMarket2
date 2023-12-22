package com.example.mymarket.ui.login.cliente.home.homeAdapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.data.dtos.Marchio
import com.example.mymarket.databinding.ItemsMarchio2Binding

class MarchioViewHolder(private val binding: ItemsMarchio2Binding) :
    RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bindMarchioCardView(cardView: Marchio) {
        binding.cardTitle2.text = "${cardView.nome}"

    }
}