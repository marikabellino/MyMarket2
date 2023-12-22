package com.example.mymarket.ui.login.cliente.home

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
import com.example.mymarket.data.dtos.Marchio
import com.example.mymarket.databinding.FragmentMarchioBinding
import com.example.mymarket.ui.cliente.home.PuntiVenditaFragment
import com.example.mymarket.ui.login.cliente.home.homeAdapter.MarchioAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MarchioFragment : Fragment() {

    private lateinit var binding: FragmentMarchioBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMarchioBinding.inflate(inflater, container, false)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbarMarchio)
        val listaMarchio = mutableListOf<Marchio>()
        apiMarchio()
        setMarchioAdapter(listaMarchio)
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
                .replace(R.id.fragment_marchio, MarchioFragment())
                .addToBackStack(null)
                .commit()
        } else if (item.itemId == R.id.menu_punti_vendita) {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_marchio, PuntiVenditaFragment())
                .addToBackStack(null)
                .commit()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setMarchioAdapter(listMarchio: List<Marchio>) {
        binding.recyclerViewMarchio.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewMarchio.adapter = MarchioAdapter(listMarchio) {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_marchio, PuntiVenditaFragment())
                .addToBackStack(null)
                .commit()
        }

    }

    private fun apiMarchio() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {

                val marchi = RetrofitInstanceKot().getItemsMarchio()

                setMarchioAdapter(marchi)

            } catch (e: Exception) {

                Snackbar.make(
                    requireActivity().findViewById(R.id.fragment_marchio),
                    "Error",
                    Snackbar.LENGTH_LONG
                ).setAction("retry") {
                    apiMarchio()
                }.show()

            }
        }
    }

}