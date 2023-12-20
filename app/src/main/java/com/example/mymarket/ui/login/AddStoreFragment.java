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
import com.example.mymarket.model.Brand;
import com.example.mymarket.model.Store;

public class AddStoreFragment extends Fragment {

    private AddStoreViewModel mViewModel;

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

        int brandId = b.getInt("brand");
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(b.getSerializable("updatedStore") != null) {
                    Store updatedStore = (Store) b.getSerializable("updatedStore");
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
                    retrofitInstance.updateStore(updatedStore.getId(), updatedStore);
                    Toast.makeText(getContext(), "Store modificato correttamente", Toast.LENGTH_SHORT).show();
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, new BrandsFragment());
                    fragmentTransaction.commit();
                } else {

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

}