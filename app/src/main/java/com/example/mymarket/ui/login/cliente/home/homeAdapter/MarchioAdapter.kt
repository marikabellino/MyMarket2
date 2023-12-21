package com.example.mymarket.ui.login.cliente.home.homeAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.data.dtos.Marchio
import com.example.mymarket.databinding.ItemsMarchioClienteBinding

class MarchioAdapter(
    private val brandList: List<Marchio>,
    private val onClickItems: (Marchio) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MarchioViewHolder(
            ItemsMarchioClienteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return brandList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MarchioViewHolder -> {
                holder.bindMarchioCardView(brandList[position])
                holder.itemView.setOnClickListener {
                    onClickItems(brandList[position])
                }
            }
        }
    }
}