package com.example.recipeapp.Repository;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.recipeapp.Models.UserModel;
import com.example.recipeapp.Utils.MyFirebaseCallBack;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserRepository {

    private static final String TAG = "UserRepository";

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    public UserRepository() {
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
    }

    //Prisijungimas prie programeles
    public void signInUserWithEmailAndPassword(String email, String password, final MyFirebaseCallBack<Boolean> myFirebaseCallBack){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.i(TAG, "signInWithEmail:success");
                            currentUser = mAuth.getCurrentUser();
                            myFirebaseCallBack.onSuccessCallback(true);
                        } else {
                            Log.i(TAG, "signInWithEmail:failure " + task.getException().getMessage());
                            myFirebaseCallBack.onFailureCallback(task.getException().getMessage());
                        }
                    }
                });
    }

    //Naujos paskyros sukurimas
    public void createUserWithEmailAndPassword(final UserModel user, final MyFirebaseCallBack<Boolean> myFirebaseCallBack ){
        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.i(TAG, "createUserWithEmail:success");
                            currentUser = mAuth.getCurrentUser();
                            myFirebaseCallBack.onSuccessCallback(true);
                        } else {
                            Log.i(TAG, "createUserWithEmail:failure", task.getException());
                            myFirebaseCallBack.onFailureCallback(task.getException().getMessage());
                        }
                    }
                });
    }
}
