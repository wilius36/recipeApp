package com.example.recipeapp.Views.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.example.recipeapp.Views.Fragments.InactiveUserFragments.FirstFragment;
import com.example.recipeapp.R;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        if (savedInstanceState == null) {
            FirstFragment f1= new FirstFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.main_frame, f1);
            fragmentTransaction.addToBackStack("FirstFragment");
            fragmentTransaction.commit();

        }

    }

}
