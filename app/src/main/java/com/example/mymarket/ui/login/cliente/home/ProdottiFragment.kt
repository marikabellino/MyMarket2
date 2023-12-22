package com.example.mymarket.ui.login.cliente.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mymarket.R

class ProdottiFragment : Fragment() {

    companion object {
        fun newInstance() = ProdottiFragment()
    }

    private lateinit var viewModel: ProdottiViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_prodotti, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProdottiViewModel::class.java)
        // TODO: Use the ViewModel
    }

}