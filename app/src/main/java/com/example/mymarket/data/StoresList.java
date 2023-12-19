package com.example.mymarket.data;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mymarket.R;
import com.example.mymarket.model.Brand;

import java.io.Serializable;

public class StoresList extends Fragment {

    private StoresListViewModel mViewModel;

    public static StoresList newInstance() {
        return new StoresList();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_stores_list, container, false);
        Bundle b = getArguments();
        Brand selectedBrand = b.getSerializable("selectedBrand", Brand.class);
        Log.e("myBrandLog", selectedBrand.getId()+"");
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(StoresListViewModel.class);
        // TODO: Use the ViewModel
    }

}