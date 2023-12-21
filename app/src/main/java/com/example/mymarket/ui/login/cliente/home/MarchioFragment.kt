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
import com.example.mymarket.data.dtos.Marchio
import com.example.mymarket.databinding.FragmentMarchioBinding
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
        val listaMarchio = mutableListOf<Marchio>()
        apiMarchio()
        setMarchioAdapter(listaMarchio)
        return binding.root
    }

    private fun setMarchioAdapter(listMarchio: List<Marchio>) {
        binding.recyclerViewMarchio.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewMarchio.adapter = MarchioAdapter(listMarchio){
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_marchio, PuntiVenditaFragment())
                .addToBackStack(null)
                .commit()
        }

    }

    private fun apiMarchio(){
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