package com.example.recipeapp.Fragments.InactiveUserFragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recipeapp.Fragments.ActiveUserFragments.MainFragment;
import com.example.recipeapp.R;
import com.example.recipeapp.ViewModels.UserViewModel;

public class LoginFragment extends Fragment {

    private View view;
    private EditText login_email, login_password;
    private Button login_button;

    private UserViewModel userViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);

        userViewModel = ViewModelProviders.of(getActivity()).get(UserViewModel.class);

        viewInitialization();

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginMethod(login_email, login_password);
            }
        });

        return view;
    }

    //Prisijungimo metodas
    private void loginMethod(EditText login_email, EditText login_password) {

        String txt_email = login_email.getText().toString();
        String txt_password = login_password.getText().toString();

        if (txt_email.isEmpty() || txt_password.isEmpty()) {
            showMessage("Užpildykite visus laukelius");
        }

        userViewModel.signInUserWithEmailAndPassword(txt_email, txt_password, new com.example.recipeapp.Utils.MyFirebaseCallBack<Boolean>() {
            @Override
            public void onSuccessCallback(Boolean object) {
                if (object) {
                    openMainFragment();
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

    //Atidaro pagrindini fragmenta
    private void openMainFragment() {
        MainFragment mainFragment= new MainFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_frame, mainFragment, "MainFragment");
        transaction.addToBackStack(null);
        transaction.commit();
    }

    //Vaizdu inicijavimas
    private void viewInitialization() {
        login_email = view.findViewById(R.id.login_email);
        login_password = view.findViewById(R.id.login_password);
        login_button = view.findViewById(R.id.login_button);
    }

    //Sukuriamas Toast kad nereiktu kurti kiekviena karta
    private void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    //Isjungia loading bar. Bei nustato visus laukelius tuscius
    private void bactToStartingViewState() {
        login_email.setText("");
        login_password.setText("");
    }

}
