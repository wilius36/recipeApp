package com.example.recipeapp.Views.Fragments.ActiveUserFragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recipeapp.Adapter.RecepieAdapter;
import com.example.recipeapp.Models.RecepieModel;
import com.example.recipeapp.R;
import com.example.recipeapp.ViewModels.RecepeViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";

    private View view;
    private RecyclerView recipeRecyclerView;
    private RecepieAdapter recepieAdapter;
    private List<RecepieModel> mRecepie;
    private RecepeViewModel recepeViewModel;

    private FirebaseFirestore firebaseFirestore;
    private CollectionReference recipesCollectionReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_main, container, false);

        recepeViewModel = ViewModelProviders.of(getActivity()).get(RecepeViewModel.class);

        firebaseFirestore = FirebaseFirestore.getInstance();
        recipesCollectionReference = firebaseFirestore.collection("recipes");

        recipeRecyclerView = view.findViewById(R.id.recipeRecyclerView);
        recipeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mRecepie = new ArrayList<>();

        updateListView();

        return view;
    }

    private void updateListView () {

        firebaseFirestore.collection("recipes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                RecepieModel recepieModel = document.toObject(RecepieModel.class);
                                mRecepie.add(recepieModel);
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                recepieAdapter = new RecepieAdapter(getContext(), mRecepie);
                                recipeRecyclerView.setAdapter(recepieAdapter);
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

    }
}
