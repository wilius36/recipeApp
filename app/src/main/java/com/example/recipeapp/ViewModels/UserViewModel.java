package com.example.recipeapp.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.recipeapp.Models.UserModel;
import com.example.recipeapp.Repository.UserRepository;
import com.example.recipeapp.Utils.MyFirebaseCallBack;

public class UserViewModel extends AndroidViewModel {

    private UserModel userModel;
    private UserRepository userRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository();
        userModel = new UserModel();
    }

    //Prisijungimas prie vartotojo profiliu
    public void signInUserWithEmailAndPassword(String email, String password, MyFirebaseCallBack<Boolean> myFirebaseCallBack){
        userRepository.signInUserWithEmailAndPassword(email, password, myFirebaseCallBack);
    }

    //Registracija naujo vartotojo
    public void createUserWithEmailAndPassword(String email, String password, MyFirebaseCallBack<Boolean> myFirebaseCallBack){
        userModel.setEmail(email);
        userModel.setPassword(password);
        userRepository.createUserWithEmailAndPassword(email, password ,myFirebaseCallBack);
    }

    //Vartotojo profilio uzsaugojimas
    public void saveUserToDataBase(){
        userRepository.saveUserToDataBase(getUser());
    }

    public void getUserData(MyFirebaseCallBack<UserModel> myFirebaseCallBack){
        userRepository.getUserData(myFirebaseCallBack);
    }

    public UserModel getUser(){
        return userModel;
    }

    public void setUserEmail (String email) {
        userModel.setEmail(email);
    }

    public void setUserUsername (String username) {
        userModel.setUsername(username);
    }

    public void setUserType (String type) {
        userModel.setUserType(type);
    }
}
