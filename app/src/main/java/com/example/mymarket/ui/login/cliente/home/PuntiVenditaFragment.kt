package com.example.mymarket.ui.cliente.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymarket.R
import com.example.mymarket.data.RetrofitInstanceKot
import com.example.mymarket.data.dtos.PuntiVendita
import com.example.mymarket.databinding.FragmentStoresListBinding
import com.example.mymarket.ui.login.cliente.home.MarchioFragment
import com.example.mymarket.ui.login.cliente.home.ProdottiFragment
import com.example.mymarket.ui.login.cliente.home.homeAdapter.PuntiVenditaAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class PuntiVenditaFragment : Fragment() {
    private lateinit var binding: FragmentStoresListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStoresListBinding.inflate(inflater, container, false)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        val listaPuntiVendita = mutableListOf<PuntiVendita>()
        apiPuntiVendita()
        setPuntiVenditaAdapter(listaPuntiVendita)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        requireActivity().menuInflater.inflate(R.menu.menu_cliente, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //catturo l id del singolo item
        if (item.itemId == R.id.menu_marchio) {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_stores_list, MarchioFragment())
                .addToBackStack(null)
                .commit()
        }

        if (item.itemId == R.id.menu_punti_vendita) {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_stores_list, PuntiVenditaFragment())
                .addToBackStack(null)
                .commit()
        }

        return super.onOptionsItemSelected(item)
    }


    private fun setPuntiVenditaAdapter(listPuntiVendita: List<PuntiVendita>) {
        binding.storesRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.storesRecycler.adapter = PuntiVenditaAdapter(listPuntiVendita){
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_stores_list, ProdottiFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun apiPuntiVendita() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {

                val puntiVendita = RetrofitInstanceKot().getItemsPuntiVendita()

                setPuntiVenditaAdapter(puntiVendita)

            } catch (e: Exception) {

                Snackbar.make(
                    requireActivity().findViewById(R.id.fragment_stores_list),
                    "Error",
                    Snackbar.LENGTH_LONG
                ).setAction("retry") {
                    apiPuntiVendita()
                }.show()

            }
        }
    }

}
