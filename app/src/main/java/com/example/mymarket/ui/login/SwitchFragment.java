package com.example.mymarket.ui.login;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mymarket.R;
import com.example.mymarket.RegisterFragment;

public class SwitchFragment extends Fragment {
    private boolean isUser;

    public SwitchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_switch, container, false);
        CardView userBtn = v.findViewById(R.id.cardView);
        CardView adminBtn = v.findViewById(R.id.cardView2);
        Button registerBtn = v.findViewById(R.id.register_button);

        //listener su user
        userBtn.setOnClickListener(v1 -> {
            isUser = true;
            Bundle bundle = new Bundle();
            bundle.putBoolean("isUser", isUser);
            LoginFragment loginFragment = new LoginFragment();
            loginFragment.setArguments(bundle);
            FragmentManager fm = getParentFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_container, loginFragment)
                    .addToBackStack(null)
                    .commit();
        });

        //listener su admin
        adminBtn.setOnClickListener(v12 -> {

            isUser = false;
            Log.e("giov", isUser+"");
            Bundle bundle = new Bundle();
            bundle.putBoolean("isUser", isUser);
            LoginFragment loginFragment = new LoginFragment();
            loginFragment.setArguments(bundle);
            FragmentManager fm = getParentFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_container, loginFragment)
                    .addToBackStack(null)
                    .commit();
        });
        registerBtn.setOnClickListener(v13 -> {
            // RegisterFragment registerFragment = new RegisterFragment();
            RegisterFragment r2f = new RegisterFragment();
            FragmentManager fm = requireActivity().getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_container, r2f);
            ft.addToBackStack(null);
            ft.commit();
        });
        return v;
    }

    public void setOnUserTypeSelectedListener() {
    }
}
