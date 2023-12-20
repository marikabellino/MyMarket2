package com.example.mymarket.data;

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

import com.example.mymarket.R;
import com.example.mymarket.RetrofitInstance;
import com.example.mymarket.StoreCallBack;
import com.example.mymarket.adapter.BrandsAdapter;
import com.example.mymarket.adapter.StoresAdapter;
import com.example.mymarket.databinding.FragmentStoresListBinding;
import com.example.mymarket.model.Brand;
import com.example.mymarket.model.Store;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StoresList extends Fragment implements StoreCallBack {

    private StoresListViewModel mViewModel;
    private RecyclerView recycler;
    private List<Store> storeList;
    private StoresAdapter storesAdapter;
    private FragmentStoresListBinding binding;
    private RetrofitInstance retrofitInstance;

    public static StoresList newInstance() {
        return new StoresList();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding =  FragmentStoresListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Bundle b = getArguments();
        int selectedBrand = b.getInt("selectedBrand",2);

        Log.e("myBrandLog", selectedBrand + " ");

        recycler = binding.storesRecycler;
        recycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        storeList = new ArrayList<>();
        storesAdapter = new StoresAdapter(storeList, requireContext(), getParentFragmentManager(), selectedBrand);

        recycler.setAdapter(storesAdapter);
        retrofitInstance = new RetrofitInstance();
        retrofitInstance.readStores(storesAdapter, this, selectedBrand);
        Log.e("ciao","sono nel brandsfragment");

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(StoresListViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onStoresDataReceived(List<Store> stores) {
        storeList.addAll(stores);
        recycler.setAdapter(storesAdapter);
        Log.e("ciao","sono nel ondataready");
    }

    @Override
    public void onStoresDataFailed(Throwable t) {

    }
}