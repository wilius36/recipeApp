package com.example.recipeapp.Views.Fragments.LogRegUserFragments;

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

    private Button first_login_button;
    private Button first_register_button;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_first, container, false);

        viewsInitialization();

        first_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(new LoginFragment());
            }
        });

        first_register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(new RegisterFragment());
            }
        });


        return view;
    }

    //Atidaro fragmenta
    private void openFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_frame, fragment);
        transaction.addToBackStack("FirstFragment");
        transaction.commit();
    }

    //Vaizdu inicijavimas
    private void viewsInitialization() {
        first_login_button = view.findViewById(R.id.first_login_button);
        first_register_button = view.findViewById(R.id.first_register_button);
    }
}
