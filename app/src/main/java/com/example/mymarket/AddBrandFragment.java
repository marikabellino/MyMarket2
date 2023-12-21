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
import com.example.mymarket.model.Store;
import com.example.mymarket.ui.login.BrandsFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddBrandFragment extends Fragment {

    private AddBrandViewModel mViewModel;
    private Brand brand;
    private String baseUrl = "https://0bb7-151-12-133-222.ngrok-free.app";

    public static AddBrandFragment newInstance() {
        return new AddBrandFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_brand, container, false);
        EditText brandNameField = v.findViewById(R.id.brandNameField);

        Bundle b = getArguments();

        if(b != null && b.containsKey("selectedBrandino")) {
            Retrofit retrofit =
                    new Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create())
                            .baseUrl(baseUrl)
                            .build();
            Service retrofitService = retrofit.create(Service.class);
            Call<Brand> call = retrofitService.getSingleBrand(b.getInt("selectedBrandino"));
            call.enqueue(new Callback<Brand>() {
                @Override
                public void onResponse(@NonNull Call<Brand> call,
                                       @NonNull Response<Brand> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        brand = response.body();
                        brandNameField.setText(brand.getBrandName().toString());
                    }
                }
                @Override
                public void onFailure(@NonNull Call<Brand> call, @NonNull Throwable t) {
                    Log.e("errorone", "errorone: " + t.getMessage());
                }
            });
        }

        Button addBrandBtn = v.findViewById(R.id.addBrandBtn);
        addBrandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b != null && b.containsKey("selectedBrandino")){
                    Log.e("oggetto", "con oggetto");
                    String newBrandName = brandNameField.getText().toString();

                    brand = new Brand();
                    brand.setBrandName(newBrandName);
                    Log.e("brandino", brand.getBrandName().toString());

                   Retrofit retrofit =
                            new Retrofit.Builder()
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .baseUrl(baseUrl)
                                    .build();
                    Service retrofitService = retrofit.create(Service.class);
                    Call<Void> call = retrofitService.editBrand(b.getInt("selectedBrandino"), brand);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(@NonNull Call<Void> call,
                                               @NonNull Response<Void> response) {
                            if (response.isSuccessful()) {
                                BrandsFragment brandsFragment = new BrandsFragment();
                                FragmentManager fm = getParentFragmentManager();
                                FragmentTransaction ft = fm.beginTransaction();
                                ft.replace(R.id.fragment_container, brandsFragment);
                                ft.addToBackStack(null);
                                ft.commit();
                                Log.e("brandino","ciandata");
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                            Log.e("errorone", "errorone: " + t.getMessage());
                        }
                    });

                } else {
                        String newBrandName = brandNameField.getText().toString().toUpperCase();
                        Brand newBrand = new Brand();
                        newBrand.setBrandName(newBrandName);
                        Log.e("oggetto", "senza oggetto");

                        RetrofitInstance retrofitInstance = new RetrofitInstance();
                        retrofitInstance.addBrand(newBrand);

                        Toast.makeText(getContext(),"Hai aggiunto: "+newBrand.getBrandName(), Toast.LENGTH_LONG).show();
                        BrandsFragment brandsFragment = new BrandsFragment();
                        FragmentManager fm = getParentFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.fragment_container, brandsFragment);
                        ft.addToBackStack(null);
                        ft.commit();
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