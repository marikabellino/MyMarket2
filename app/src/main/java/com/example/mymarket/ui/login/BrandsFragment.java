package com.example.mymarket.ui.login;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import android.widget.Button;
import android.widget.Toast;

import com.example.mymarket.AddBrandFragment;
import com.example.mymarket.DataCallback;
import com.example.mymarket.R;
import com.example.mymarket.RetrofitInstance;
import com.example.mymarket.adapter.BrandsAdapter;
import com.example.mymarket.databinding.FragmentBrandsBinding;
import com.example.mymarket.model.Brand;

import java.util.ArrayList;
import java.util.List;

public class BrandsFragment extends Fragment implements DataCallback {

    private BrandsViewModel mViewModel;
    private RecyclerView lista;
    private List<Brand> brandList;
    private FragmentBrandsBinding binding;
    private BrandsAdapter brandsAdapter;
    private RetrofitInstance retrofitInstance;

    public static BrandsFragment newInstance() {
        return new BrandsFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentBrandsBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        //Gestione RV
        lista = binding.brandsRecycler;
        lista.setLayoutManager(new LinearLayoutManager(requireContext()));
        brandList = new ArrayList<>();
        Bundle bundle = getArguments();
        bundle.getString("ciao");
        String ciao = bundle.getString("ciao");
        brandsAdapter = new BrandsAdapter(brandList, requireContext(), getParentFragmentManager(), ciao);

        lista.setAdapter(brandsAdapter);
        retrofitInstance = new RetrofitInstance();
        retrofitInstance.readBrands(brandsAdapter, this);
        Log.e("ciao","sono nel brandsfragment");

        Button button = root.findViewById(R.id.addBrandBtn2);
        if(ciao.equalsIgnoreCase("invisibile")){
            button.setVisibility(View.INVISIBLE);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddBrandFragment addFragment = new AddBrandFragment();
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, addFragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BrandsViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onDataReady(List<Brand> l) {
        brandList.addAll(l);
        lista.setAdapter(brandsAdapter);
        Log.e("ciao","sono nel ondataready");

    }


    @Override
    public void onCustomerDataFailed(Throwable t) {
        Log.e("errore","ciao: " + t.getMessage());
    }
}

