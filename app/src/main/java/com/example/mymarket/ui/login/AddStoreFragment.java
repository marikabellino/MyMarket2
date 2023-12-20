package com.example.mymarket.ui.login;

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

import com.example.mymarket.R;
import com.example.mymarket.RetrofitInstance;
import com.example.mymarket.Service;
import com.example.mymarket.SingleStoreCallBack;
import com.example.mymarket.model.Brand;
import com.example.mymarket.model.Store;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddStoreFragment extends Fragment implements SingleStoreCallBack {

    private AddStoreViewModel mViewModel;
    private Store store;
    private String baseUrl = "https://7f83-151-12-133-222.ngrok-free.app";

    public static AddStoreFragment newInstance() {
        return new AddStoreFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_add_store, container, false);
        EditText city = v.findViewById(R.id.store_city);
        EditText cap = v.findViewById(R.id.store_cap);
        EditText address = v.findViewById(R.id.store_address);
        EditText civico = v.findViewById(R.id.store_num);
        Button insertBtn = v.findViewById(R.id.insertStore);
        Bundle b = getArguments();

        if(b.getInt("updatedStoreId") != 0) {
            Log.e("ciao","sono ciao");

            Retrofit retrofit =
                    new Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create())
                            .baseUrl(baseUrl)
                            .build();
            Service retrofitService = retrofit.create(Service.class);
            Call<Store> call = retrofitService.getSingleStore(b.getInt("updatedStoreId"));
            call.enqueue(new Callback<Store>() {
                @Override
                public void onResponse(@NonNull Call<Store> call,
                                       @NonNull Response<Store> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        store = response.body();
                        city.setText(store.getCitta().toString());
                        cap.setText("" + store.getCAP());
                        address.setText(store.getIndirizzo().toString());
                        civico.setText(store.getCivico().toString());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Store> call, @NonNull Throwable t) {
                    Log.e("errorone", "errorone: " + t.getMessage());
                }
            });
        }

        int brandId = b.getInt("brand");
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ciao","ciaone cliccato");
                if(b.getInt("updatedStoreId") != 0) {

                    Log.e("oggetto","con oggetto");

                    String newcity = city.getText().toString();
                    int newCap = Integer.parseInt(cap.getText().toString());
                    String newAddress = address.getText().toString();
                    String newCivico = civico.getText().toString();

                    Log.e("oggetto","con oggetto" + newcity + " " + newCap + " " + newAddress + " " + newCivico + " " + store.getId());

                    store.setCAP(newCap);
                    store.setCitta(newcity);
                    store.setIndirizzo(newAddress);
                    store.setCivico(newCivico);

                    Retrofit retrofit =
                            new Retrofit.Builder()
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .baseUrl(baseUrl)
                                    .build();
                    Service retrofitService = retrofit.create(Service.class);
                    Call<Void> call = retrofitService.updateStore(store.getId(), store);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(@NonNull Call<Void> call,
                                               @NonNull Response<Void> response) {
                            if (response.isSuccessful())
                            {
                                Log.e("oggetto","ciaone si " + store.toString());
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                            Log.e("oggetto","ciaone no");
                        }
                    });
                    Toast.makeText(getContext(), "Store modificato correttamente", Toast.LENGTH_SHORT).show();
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, new BrandsFragment());
                    fragmentTransaction.commit();
                } else {
                    Log.e("oggetto","senza oggetto");
                    String newcity = city.getText().toString();
                    int newCap = Integer.parseInt(cap.getText().toString());
                    String newAddress = address.getText().toString();
                    String newCivico = civico.getText().toString();
                    Store newStore = new Store();
                    newStore.setCAP(newCap);
                    newStore.setCitta(newcity);
                    newStore.setIndirizzo(newAddress);
                    newStore.setCivico(newCivico);
                    RetrofitInstance retrofitInstance = new RetrofitInstance();
                    retrofitInstance.addStore(brandId, newStore);
                    Toast.makeText(getContext(), "Store aggiunto correttamente", Toast.LENGTH_SHORT).show();
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, new BrandsFragment());
                    //fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }

            }
        });
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AddStoreViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onSingleStoreDataReceived(Store storel) {
        store = storel;
    }

    @Override
    public void onSingleStoreDataFailed(Throwable t) {
        t.printStackTrace();
    }
}