package com.example.mymarket;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.mymarket.model.User;
import com.example.mymarket.ui.login.cliente.home.MarchioFragment;

public class RegisterFragment extends Fragment {

    private RegisterViewModel mViewModel;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register, container, false);
        EditText name = v.findViewById(R.id.register_name);
        EditText surname = v.findViewById(R.id.register_surname);
        EditText email = v.findViewById(R.id.register_mail);
        EditText pwd = v.findViewById(R.id.register_pwd);
        EditText pwdConfirmation = v.findViewById(R.id.register_pwdConfirm);
        RadioButton userRadio = v.findViewById(R.id.user_radio);
        RadioButton adminRadio = v.findViewById(R.id.admin_radio);
        Button registerBtn = v.findViewById(R.id.register_btn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = name.getText().toString();
                String newSurname = surname.getText().toString();
                String newEmail = email.getText().toString();
                String newPwd = pwd.getText().toString();
                String newPwdConf = pwdConfirmation.getText().toString();
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                if (!newName.equals("") && !newEmail.equals("") && !newSurname.equals("") && !newPwd.equals("")) {
                    if (newPwd.equals(newPwdConf)) {
                        User user = new User();
                        user.setNome(newName);
                        user.setEmail(newEmail);
                        user.setCognome(newSurname);
                        user.setPassword(newPwd);
                        if (userRadio.isChecked()) {
                            user.setAdmin(false);
                            Log.e("Ruolo", "sono un utente");
                            RetrofitInstance retrofitInstance = new RetrofitInstance();
                            retrofitInstance.addUser(user);
                            SharedPreferencesManager.saveUserData(requireContext(), user);
                            MarchioFragment marchioFragment = new MarchioFragment();
                            fragmentTransaction.replace(R.id.register_frag, marchioFragment)
                                    .addToBackStack(null)
                                    .commit();

                        } else if (adminRadio.isChecked()) {

                            user.setAdmin(true);
                            Log.e("Ruolo", "sono un amministratore");
                            RetrofitInstance retrofitInstance = new RetrofitInstance();
                            retrofitInstance.addUser(user);
                            SharedPreferencesManager.saveAdminData(requireContext(), user);
                            DashboardFragment dashboardFragment = new DashboardFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("username", user.getNome());
                            dashboardFragment.setArguments(bundle);
                            fragmentTransaction.replace(R.id.register_frag, dashboardFragment)
                                    .addToBackStack(null)
                                    .commit();
                        }
                    } else {
                        Toast.makeText(getContext(), "Le password non coincidono", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Compila tutti i campi", Toast.LENGTH_LONG).show();
                }
            }
        });
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        // TODO: Use the ViewModel
    }

}