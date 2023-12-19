package com.example.mymarket;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.mymarket.model.Brand;
import com.example.mymarket.ui.login.BrandsFragment;

public class AddBrandFragment extends Fragment {

    private AddBrandViewModel mViewModel;

    public static AddBrandFragment newInstance() {
        return new AddBrandFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_brand, container, false);
        EditText brandNameField = v.findViewById(R.id.brandNameField);
        Button addBrandBtn = v.findViewById(R.id.addBrandBtn);
        addBrandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (brandNameField.getText().toString()!=""){
                    String newBrandName = brandNameField.getText().toString().toUpperCase();
                    Brand newBrand = new Brand();
                    newBrand.setBrandName(newBrandName);
                    Log.e("logBrnd", newBrand.getBrandName());
                    RetrofitInstance retrofitInstance = new RetrofitInstance();
                    retrofitInstance.addBrand(newBrand);
                    brandNameField.setText("");
                    Toast.makeText(getContext(),"Hai aggiunto: "+newBrand.getBrandName(), Toast.LENGTH_LONG).show();
                    BrandsFragment brandsFragment = new BrandsFragment();
                    FragmentManager fm = getParentFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.fragment_container, brandsFragment);
                    ft.addToBackStack(null);
                    ft.commit();
                }else {
                    Toast.makeText(getContext(),"Inserisci il nome del brand", Toast.LENGTH_LONG).show();
                }

            }
        });
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AddBrandViewModel.class);
        // TODO: Use the ViewModel
    }
}