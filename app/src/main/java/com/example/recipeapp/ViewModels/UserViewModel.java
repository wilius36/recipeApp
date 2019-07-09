package com.example.recipeapp.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.recipeapp.Models.UserModel;
import com.example.recipeapp.Repository.UserRepository;
import com.example.recipeapp.Utils.MyFirebaseCallBack;
import com.google.firebase.auth.FirebaseUser;

public class UserViewModel extends AndroidViewModel {

    private UserModel userModel;
    private UserRepository userRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository();
        userModel = new UserModel();
    }

    //Patikrina ar vartotojas yra prisijunges
    public FirebaseUser checkIfUserLoggedIn(){
        return userRepository.checkIfUserLoggedIn();
    }

    //Prisijungimas prie vartotojo profiliu
    public void signInUserWithEmailAndPassword(String email, String password, MyFirebaseCallBack<Boolean> myFirebaseCallBack){
        userRepository.signInUserWithEmailAndPassword(email, password, myFirebaseCallBack);
    }

    //Registracija naujo vartotojo
    public void createUserWithEmailAndPassword(String email, String password, MyFirebaseCallBack<Boolean> myFirebaseCallBack){
        userRepository.createUserWithEmailAndPassword(email, password ,myFirebaseCallBack);
    }

    //Vartotojo profilio uzsaugojimas
    public void saveUserToDataBase(UserModel userModel, MyFirebaseCallBack<Boolean> myFirebaseCallBack){
        userRepository.saveUserToDataBase(userModel, myFirebaseCallBack);
    }

    public void getUserData(MyFirebaseCallBack<UserModel> myFirebaseCallBack){
        userRepository.getUserData(myFirebaseCallBack);
    }

}
