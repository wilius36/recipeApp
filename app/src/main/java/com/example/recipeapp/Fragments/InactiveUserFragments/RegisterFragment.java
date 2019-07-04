package com.example.recipeapp.Fragments.InactiveUserFragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.recipeapp.R;
import com.example.recipeapp.ViewModels.UserViewModel;

public class RegisterFragment extends Fragment {

    private View view;
    private Switch switch1;
    private EditText register_username, register_email, register_password;
    private String userType = "user";
    private Button register_button;
    private UserViewModel userViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register, container, false);

        userViewModel = ViewModelProviders.of(getActivity()).get(UserViewModel.class);

        switch1 = view.findViewById(R.id.switch1);
        register_username = view.findViewById(R.id.register_username);
        register_email = view.findViewById(R.id.register_email);
        register_password = view.findViewById(R.id.register_password);
        register_button = view.findViewById(R.id.register_button);

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registrationMethod(register_email, register_password);
            }
        });

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    register_username.setVisibility(View.VISIBLE);
                    userType = "creator";
                } else {
                    register_username.setVisibility(View.GONE);
                    userType = "user";
                }
            }
        });

        return view;
    }

    //Registracijos metodas
    private void registrationMethod (EditText register_email, EditText register_password) {

        String txt_email = register_email.getText().toString();
        String txt_password = register_password.getText().toString();

        if (txt_email.isEmpty() || txt_password.isEmpty()) {
            showMessage("Užpildykite visus laukelius");
        }

        userViewModel.createUserWithEmailAndPassword(txt_email, txt_password, new com.example.recipeapp.Utils.MyFirebaseCallBack<Boolean>() {
            @Override
            public void onSuccessCallback(Boolean object) {
                if (object) {
                    showMessage("Pavyko");
                } else {
                    showMessage("Įvyko klaida");
                    bactToStartingViewState();
                }
            }

            @Override
            public void onFailureCallback(String message) {
                showMessage(message);
                bactToStartingViewState();
            }
        });
    }

    //Sukuriamas Toast kad nereiktu kurti kiekviena karta
    private void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    //Isjungia loading bar. Bei nustato visus laukelius tuscius
    private void bactToStartingViewState() {
        register_username.setText("");
        register_email.setText("");
        register_password.setText("");
    }
}
