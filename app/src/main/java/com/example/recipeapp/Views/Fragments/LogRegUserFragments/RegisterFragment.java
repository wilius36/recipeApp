package com.example.recipeapp.Views.Fragments.LogRegUserFragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.recipeapp.Models.UserModel;
import com.example.recipeapp.R;
import com.example.recipeapp.Utils.MyFirebaseCallBack;
import com.example.recipeapp.ViewModels.UserViewModel;
import com.example.recipeapp.Views.Activities.MainActivity;

public class RegisterFragment extends Fragment {

    private View view;
    private Switch switch1;
    private EditText register_username;
    private EditText register_email;
    private EditText register_password;
    private Button register_button;
    private UserViewModel userViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register, container, false);

        userViewModel = ViewModelProviders.of(getActivity()).get(UserViewModel.class);

        viewsInitialization();

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registrationMethod();
            }
        });

        return view;
    }

    //Vaizdu inicijavimas
    private void viewsInitialization() {
        switch1 = view.findViewById(R.id.switch1);
        register_username = view.findViewById(R.id.register_username);
        register_email = view.findViewById(R.id.register_email);
        register_password = view.findViewById(R.id.register_password);
        register_button = view.findViewById(R.id.register_button);
    }

    //Registracijos metodas
    private void registrationMethod () {

        String txt_email = register_email.getText().toString();
        String txt_password = register_password.getText().toString();

        String userType = "user";

        if (switch1.isChecked()) {
            userType = "creator";
        }

        final UserModel userModel = new UserModel();

        userModel.setUsername(register_username.getText().toString());
        userModel.setEmail(register_email.getText().toString());
        userModel.setUserType(userType);

        if (txt_email.isEmpty() || txt_password.isEmpty()) {
            showMessage("Užpildykite visus laukelius");
        }

        userViewModel.createUserWithEmailAndPassword(txt_email, txt_password, new MyFirebaseCallBack<Boolean>() {
            @Override
            public void onSuccessCallback(Boolean object) {
                if (object) {
                    userViewModel.saveUserToDataBase(userModel, new MyFirebaseCallBack<Boolean>() {
                        @Override
                        public void onSuccessCallback(Boolean object) {
                            openMainActivity();
                        }

                        @Override
                        public void onFailureCallback(String message) {
                            showMessage(message);
                        }
                    });

                }
            }

            @Override
            public void onFailureCallback(String message) {
                showMessage(message);
                bactToStartingViewState();
            }
        });
    }

    //Atidaro kita langa
    private void openMainActivity() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    //Sukuriamas Toast kad nereiktu kurti kiekviena karta
    private void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    //Nustato visus laukelius tuscius
    private void bactToStartingViewState() {
        register_username.setText("");
        register_email.setText("");
        register_password.setText("");
    }
}
