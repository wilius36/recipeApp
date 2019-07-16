package com.example.recipeapp.Views.Fragments.ActiveUserFragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recipeapp.Adapter.RecyclerViewDataAdapter;
import com.example.recipeapp.Models.RecipeModel;
import com.example.recipeapp.R;
import com.example.recipeapp.ViewModels.RecipeViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";

    private View view;
    private RecyclerView mainRecyclerView;
    private List<String> mDishType;
    private List<RecipeModel> mRecepies;
    private RecipeViewModel recepeViewModel;

    private FirebaseFirestore firebaseFirestore;
    private CollectionReference recipesCollectionReference;

    private List<List<RecipeModel>> sarasuSarasas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_main, container, false);

        recepeViewModel = ViewModelProviders.of(getActivity()).get(RecipeViewModel.class);

        firebaseFirestore = FirebaseFirestore.getInstance();
        recipesCollectionReference = firebaseFirestore.collection("recipes");

        mDishType = new ArrayList<>();
        mRecepies = new ArrayList<>();
        sarasuSarasas = new ArrayList<>();

        mainRecyclerView = view.findViewById(R.id.mainRecyclerView);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        String[] stringArray = getResources().getStringArray(R.array.dish_type_array);
        Collections.addAll(mDishType, stringArray);
        mDishType.remove("Pasirinkite patiekalo tipą");

        recipesCollectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        mRecepies.clear();
                        sarasuSarasas.clear();
                        Log.d(TAG,document.getId() + " => " + document.getData());
                        RecipeModel recipeModel = document.toObject(RecipeModel.class);
                        mRecepies.add(recipeModel);

                    }
                    sarasuSarasas.add(mRecepies);

                    Log.d(TAG, "SARASAS" + sarasuSarasas + "");
                    Log.d(TAG, "RECIPES" + mRecepies + "");
                    //Log.d(TAG, Arrays.toString(mRecepies.toArray()));

                    RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(sarasuSarasas,getContext());
                    mainRecyclerView.setAdapter(adapter);


                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });

        /*for (final String s : mDishType) {

            recipesCollectionReference
                    .whereEqualTo("mealType", s)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    mRecepies.clear();
                                    sarasas.clear();

                                    RecipeModel recipeModel = document.toObject(RecipeModel.class);
                                    mRecepies.add(recipeModel);

                                }

                                sarasas.add(mRecepies);

                                Log.d(TAG, Arrays.toString(mRecepies.toArray()));

                                RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(sarasas,getContext());
                                mainRecyclerView.setAdapter(adapter);


                            } else {
                                Log.d(TAG, "Error getting documents: ", task.getException());
                            }
                        }
                    });

        } */

        return view;
    }
}