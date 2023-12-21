package com.example.mymarket.ui.login;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mymarket.DashboardFragment;
import com.example.mymarket.R;
import com.example.mymarket.RetrofitInstance;
import com.example.mymarket.SharedPreferencesManager;
import com.example.mymarket.data.interfaces.SingleUserCallback;
import com.example.mymarket.model.User;
import com.example.mymarket.ui.cliente.home.MarchioFragment;
import com.google.android.material.snackbar.Snackbar;

public class LoginFragment extends Fragment implements SingleUserCallback {
    public static final String ARG_IS_USER = "isUser";
    private OnUserTypeSelectedListener userTypeSelectedListener;
    String u;
    String p;
    boolean isUser;
    User user = null;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        Bundle bundle = getArguments();
        isUser = bundle.getBoolean("isUser");

       Button login = v.findViewById(R.id.my_login_btn);

        EditText username = v.findViewById(R.id.username);
        EditText pw = v.findViewById(R.id.password);


        String sharedEmail;
        String sharedPassword;

        if (isUser) {
            user = SharedPreferencesManager.getUserData(getContext());
            sharedEmail = user.getEmail();
            sharedPassword = user.getPassword();
            username.setText(sharedEmail);
            pw.setText(sharedPassword);
        } else {
            user = SharedPreferencesManager.getAdminData(getContext());
            sharedEmail = user.getEmail();
            sharedPassword = user.getPassword();
            username.setText(sharedEmail);
            pw.setText(sharedPassword);
        }

        //PULSANTE DI LOGIN
        login.setOnClickListener(view -> {
            Log.e("giov", "login cliccato");
            u = username.getText().toString();
            p = pw.getText().toString();

            if (u.equals(user.getEmail()) && p.equals(user.getPassword())) {


                //controllo campi vuoti + presenza shared e redirect
                if (user != null && user.getAdmin() && !isUser && !u.equals("") && !p.equals("")) {
                    Log.e("giov", "sono qui, ciaone");
                    Bundle b = new Bundle();
                    b.putString("username", user.getNome());
                    DashboardFragment dashboardFragment = new DashboardFragment();
                    dashboardFragment.setArguments(b);
                    goToFragment(dashboardFragment);
                } else if (user != null && user.getId() != -1 && !u.equals("") && !p.equals("")) {
                    goToFragment(new MarchioFragment());
                    Log.e("giov", "sono qui, ciaone dal marchio");
                } else if (!u.equals("") && !p.equals("")) {
                    RetrofitInstance retrofitInstance = new RetrofitInstance();
                    retrofitInstance.singleUser(u, this);
                } else {
                    //controllo campi vuoti
                    Toast.makeText(getContext(), "compila tutti i campi", Toast.LENGTH_LONG).show();
                }
            }else{
                if (!u.equals("") && !p.equals("")) {
                    RetrofitInstance retrofitInstance = new RetrofitInstance();
                    retrofitInstance.singleUser(u, this);
                }else{
                    Toast.makeText(getContext(), "credenziali errate", Toast.LENGTH_LONG).show();
                }
            }
        });

        return v;
    }

    private void goToFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_login, fragment);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    @Override
    public void onSingleUserDataReceived(User user) {

        if (user != null) {

            if (user.getEmail().equals(u) && user.getPassword().equals(p)) {
                if (user.getAdmin() != isUser) {
                    if (user.getAdmin()) {
                        Bundle b = new Bundle();
                        b.putString("username", user.getNome());
                        DashboardFragment dashboardFragment = new DashboardFragment();
                        dashboardFragment.setArguments(b);
                        goToFragment(dashboardFragment);
                    } else {
                        goToFragment(new MarchioFragment());
                    }
                } else {
                    Snackbar.make(
                            requireActivity().findViewById(R.id.fragment_container_login),
                            "Sezione utente errata",
                            Snackbar.LENGTH_LONG
                    ).show();
                }

            } else {
                Snackbar.make(
                        requireActivity().findViewById(R.id.fragment_container_login),
                        "Le credenziali sono errate",
                        Snackbar.LENGTH_LONG
                ).show();
            }

        } else {
            Snackbar.make(
                    requireActivity().findViewById(R.id.fragment_container_login),
                    "Utente non esistente",
                    Snackbar.LENGTH_LONG
            ).show();
        }
    }

    @Override
    public void onSingleUserDataFailed(Throwable t) {

    }
}
