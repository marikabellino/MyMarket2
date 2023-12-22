package com.example.mymarket.ui.login.cliente.home.homeAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.data.dtos.PuntiVendita
import com.example.mymarket.databinding.ItemsStore2Binding

class PuntiVenditaAdapter(
    private val puntiVenditaList : List<PuntiVendita>,
    private val onClickItems: (PuntiVendita) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PuntiVenditaViewHolder(
            ItemsStore2Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return puntiVenditaList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PuntiVenditaViewHolder -> {
                holder.bindPuntiVenditaCardView(puntiVenditaList[position])
                holder.itemView.setOnClickListener {
                    onClickItems(puntiVenditaList[position])
                }
            }
        }
    }
}