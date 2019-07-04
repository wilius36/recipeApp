package com.example.recipeapp.Fragments.InactiveUserFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.recipeapp.R;

public class FirstFragment extends Fragment {

    private Button first_login_button, first_register_button;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_first, container, false);

        viewInitialization();

        first_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginFragment();
            }
        });

        first_register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterFragment();
            }
        });


        return view;
    }

    //Atidaro registracijos fragmenta
    private void openRegisterFragment() {
        RegisterFragment registerFragment= new RegisterFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_frame, registerFragment, "RegisterFragment");
        transaction.addToBackStack("FirstFragment");
        transaction.commit();
    }

    //Atidaro prisijungimo fragmenta
    private void openLoginFragment() {
        LoginFragment loginFragment= new LoginFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_frame, loginFragment, "LoginFragment");
        transaction.addToBackStack("FirstFragment");
        transaction.commit();
    }

    //Vaizdu inicijavimas
    private void viewInitialization() {
        first_login_button = view.findViewById(R.id.first_login_button);
        first_register_button = view.findViewById(R.id.first_register_button);
    }
}
