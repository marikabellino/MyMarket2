package com.example.mymarket.ui.login;

import android.os.Bundle;
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
    private OnUserTypeSelectedListener userTypeSelectedListener;

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
        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isUser = true;
                userTypeSelectedListener.onUserTypeSelected(isUser);
            }
        });

        //listener su admin
        adminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isUser = false;
                userTypeSelectedListener.onUserTypeSelected(isUser);
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // RegisterFragment registerFragment = new RegisterFragment();
                RegisterFragment r2f = new RegisterFragment();
                FragmentManager fm = requireActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, r2f);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return v;
    }

    public void setOnUserTypeSelectedListener(OnUserTypeSelectedListener listener) {
        this.userTypeSelectedListener = listener;
    }
}
