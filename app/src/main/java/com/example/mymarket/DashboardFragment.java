package com.example.mymarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymarket.data.StoresList;
import com.example.mymarket.ui.login.BrandsFragment;


public class DashboardFragment extends Fragment {

    private DashboardViewModel mViewModel;

    public static DashboardFragment newInstance() {
        return new DashboardFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_dashboard, container, false);
        Bundle bundle = getArguments();
        String user = bundle.getString("username","");
        TextView welcome = v.findViewById(R.id.welcomeTextView);
        welcome.setText("Ciao "+user+" !");

        //toolbar
        Toolbar toolbar = v.findViewById(R.id.toolbar);
       // AppCompatActivity activity = (AppCompatActivity) getActivity();
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
        //activity.setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        Log.e("mylog", "onCreateOptionsMenu");
        //carica il menu utilizzando il metodo getMenuInflate()
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getMenuInflater().inflate(R.menu.menu_main, menu);

    }

    //listner  sulla selezione delgi item di menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        FragmentManager fm = getParentFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        //catturo l id del singolo item
        int id= item.getItemId();
        if (id==R.id.menu_brand){
            BrandsFragment brandsFragment = new BrandsFragment();
            ft.replace(R.id.fragment_container, brandsFragment)
                    .addToBackStack(null)
                    .commit();
        } else if (id==R.id.menu_stores) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("booleano", true);
            StoresList storesList = new StoresList();
            storesList.setArguments(bundle);
            ft.replace(R.id.fragment_container, storesList)
                    .addToBackStack(null)
                    .commit();
        }else if (id==R.id.menu_edit) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("visibility", true);
            BrandsFragment brandsFragment = new BrandsFragment();
            brandsFragment.setArguments(bundle);
            ft.replace(R.id.fragment_container, brandsFragment)
                    .addToBackStack(null)
                    .commit();
        }


        return super.onOptionsItemSelected(item);
    }
}