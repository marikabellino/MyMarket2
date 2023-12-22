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

import com.example.mymarket.adapter.BrandsAdapter;
import com.example.mymarket.adapter.ProductAdapter;
import com.example.mymarket.databinding.FragmentCategorieBinding;
import com.example.mymarket.databinding.FragmentProductBinding;
import com.example.mymarket.model.Prodotto;
import com.example.mymarket.ui.login.ProdottoCallback;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment implements ProdottoCallback {

    private ProductViewModel mViewModel;
    private FragmentProductBinding binding;

    private RecyclerView recyclerProd;

    private List<Prodotto> listaProdotto;

    private ProductAdapter productAdapter;
    private RetrofitInstance retrofitInstance;

    public static ProductFragment newInstance() {
        return new ProductFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentProductBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Log.e("product","product fragment");

        recyclerProd = binding.productsRecycler;
        recyclerProd.setLayoutManager(new LinearLayoutManager(requireContext()));
        listaProdotto = new ArrayList<>();

        productAdapter = new ProductAdapter(listaProdotto,getParentFragmentManager(), requireContext());
        recyclerProd.setAdapter(productAdapter);
        retrofitInstance = new RetrofitInstance();
        retrofitInstance.readAllProducts(productAdapter, this, "PESCHERIA_E_MACELLERIA");
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onProductReceived(List<Prodotto> prodotti) {
            listaProdotto.addAll(prodotti);
            productAdapter.notifyDataSetChanged();
    }

    @Override
    public void onProductFailure(Throwable t) {

    }
}