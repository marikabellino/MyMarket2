package com.example.mymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.mymarket.ui.login.LoginFragment;
import com.example.mymarket.ui.login.OnUserTypeSelectedListener;
import com.example.mymarket.ui.login.SwitchFragment;

public class MainActivity extends AppCompatActivity implements OnUserTypeSelectedListener {
//ciao
    @Override
    //ciao
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inizializza il fragment solo se l'attività è appena stata creata
        if (savedInstanceState == null) {
            SwitchFragment switchFragment = new SwitchFragment();
            switchFragment.setOnUserTypeSelectedListener(this);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, switchFragment)
                    .commit();
        }
    }

    @Override
    public void onUserTypeSelected(boolean isUser) {
        // Aggiungi il fragment LoginFragment al fragment_container
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        LoginFragment loginFragment = new LoginFragment();

        // Passa l'informazione se l'utente è un cliente o un admin al LoginFragment
        Bundle args = new Bundle();
        args.putBoolean(LoginFragment.ARG_IS_USER, isUser);
        loginFragment.setArguments(args);
        transaction.replace(R.id.fragment_container, loginFragment);
        transaction.addToBackStack(null);
        transaction.commit();
        Log.e("ciao", "onUserTypeSelected - isUser: " + isUser);

    }
}
