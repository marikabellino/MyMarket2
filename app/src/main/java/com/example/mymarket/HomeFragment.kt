package com.example.mymarket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mymarket.databinding.FragmentHomeBinding
import com.example.mymarket.ui.login.cliente.home.MarchioFragment
import com.example.mymarket.ui.cliente.home.PuntiVenditaFragment

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val user = requireArguments().getString("username", "")
        binding.welcomeHomeTextView.text = "Ciao ${user} !"
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbarHome)
        setHasOptionsMenu(true)
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
                .replace(R.id.fragment_container, MarchioFragment())
                .addToBackStack(null)
                .commit()
        } else if (item.itemId == R.id.menu_punti_vendita) {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, PuntiVenditaFragment())
                .addToBackStack(null)
                .commit()
        }
        return super.onOptionsItemSelected(item)
    }

}