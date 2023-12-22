package com.example.mymarket;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mymarket.adapter.CategoryAdapter;
import com.example.mymarket.adapter.StoresAdapter;
import com.example.mymarket.databinding.FragmentCategorieBinding;
import com.example.mymarket.databinding.FragmentStoresListBinding;

import java.util.ArrayList;
import java.util.List;

public class CategorieFragment extends Fragment {

    private CategorieViewModel mViewModel;
    private FragmentCategorieBinding binding;

    private String listaCategorie[] = {"Frutta e verdura", "Pescheria e macelleria", "Bevande", "Igiene", "Panificio", "Elettronica"};

    public static CategorieFragment newInstance() {
        return new CategorieFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCategorieBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        RecyclerView recycler = binding.categoryRecycler;
        recycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        CategoryAdapter categoryAdapter = new CategoryAdapter(listaCategorie,getParentFragmentManager(),getContext());
        recycler.setAdapter(categoryAdapter);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CategorieViewModel.class);
        // TODO: Use the ViewModel
    }

}