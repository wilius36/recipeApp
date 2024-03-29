package com.example.recipeapp.Repository;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.recipeapp.Models.UserModel;
import com.example.recipeapp.Utils.MyFirebaseCallBack;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {

    private static final String TAG = "UserRepository";

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private FirebaseFirestore firebaseFirestore;
    private CollectionReference userCollectionReference;

    public UserRepository() {
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();
        userCollectionReference = firebaseFirestore.collection("users");
    }

    //Patikrina ar vartotojas yra prisijunges
    public FirebaseUser checkIfUserLoggedIn(){
        return mAuth.getCurrentUser();
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
    public void createUserWithEmailAndPassword(String email, String password, final MyFirebaseCallBack<Boolean> myFirebaseCallBack ){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.i(TAG, "createUserWithEmail:success");
                            myFirebaseCallBack.onSuccessCallback(true);
                        } else {
                            Log.i(TAG, "createUserWithEmail:failure", task.getException());
                            myFirebaseCallBack.onFailureCallback(task.getException().getMessage());
                        }
                    }
                });
    }

    //Vartotojo profilio uzsaugojimas
    public void saveUserToDataBase (UserModel userModel, final MyFirebaseCallBack<Boolean> myFirebaseCallBack) {

        String userId = currentUser.getUid();

        Map<String, Object> user = new HashMap<>();
        user.put("username", userModel.getUsername());
        user.put("email", userModel.getEmail());
        user.put("type", userModel.getUserType());

        userCollectionReference.document(userId)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "DocumentSnapshot successfully written!");
                myFirebaseCallBack.onSuccessCallback(true);
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                        myFirebaseCallBack.onFailureCallback(e.getMessage());
                    }
                });
    }

    //Vartotojo profilio duomenu gavimas
    public void getUserData (final MyFirebaseCallBack<UserModel> myFirebaseCallBack) {
        userCollectionReference.document(currentUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        myFirebaseCallBack.onSuccessCallback(document.toObject(UserModel.class));
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }

    //Patikrinamas vartotojo tipas
    public void checkUserType (final MyFirebaseCallBack<String> myFirebaseCallBack){

        userCollectionReference.document(currentUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String value = document.getString("type");
                        if (value.equals("creator")){
                            myFirebaseCallBack.onSuccessCallback("creator");
                        } else {
                            myFirebaseCallBack.onSuccessCallback("user");
                        }
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

    }

}
