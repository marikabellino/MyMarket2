package com.example.mymarket.ui.cliente.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymarket.R
import com.example.mymarket.data.RetrofitInstanceKot
import com.example.mymarket.data.dtos.PuntiVendita
import com.example.mymarket.databinding.FragmentPuntiVenditaBinding
import com.example.mymarket.ui.login.cliente.home.homeAdapter.PuntiVenditaAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class PuntiVenditaFragment : Fragment() {
    private lateinit var binding: FragmentPuntiVenditaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPuntiVenditaBinding.inflate(inflater, container, false)
        val listaPuntiVendita = mutableListOf<PuntiVendita>()
        apiPuntiVendita()
        setPuntiVenditaAdapter(listaPuntiVendita)
        return binding.root
    }

    private fun setPuntiVenditaAdapter(listPuntiVendita: List<PuntiVendita>) {
        binding.recyclerViewPuntiVendita.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewPuntiVendita.adapter = PuntiVenditaAdapter(listPuntiVendita)
    }

    private fun apiPuntiVendita() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {

                val puntiVendita = RetrofitInstanceKot().getItemsPuntiVendita()

                setPuntiVenditaAdapter(puntiVendita)

            } catch (e: Exception) {

                Snackbar.make(
                    requireActivity().findViewById(R.id.fragment_punti_vendita),
                    "Error",
                    Snackbar.LENGTH_LONG
                ).setAction("retry") {
                    apiPuntiVendita()
                }.show()

            }
        }
    }

}
