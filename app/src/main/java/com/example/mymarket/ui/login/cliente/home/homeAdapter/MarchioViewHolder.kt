package com.example.mymarket.ui.login.cliente.home.homeAdapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.data.dtos.Marchio
import com.example.mymarket.databinding.ItemsMarchioBinding
import com.example.mymarket.databinding.ItemsMarchioClienteBinding

class MarchioViewHolder(private val binding: ItemsMarchioClienteBinding) :
    RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bindMarchioCardView(cardView: Marchio) {
        binding.cardTitle.text = "${cardView.nome}"

    }
}