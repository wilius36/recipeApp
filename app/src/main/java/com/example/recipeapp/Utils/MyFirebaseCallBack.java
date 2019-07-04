package com.example.recipeapp.Utils;

public interface MyFirebaseCallBack<T> {

    void onSuccessCallback(T object);

    void onFailureCallback(String message);
}