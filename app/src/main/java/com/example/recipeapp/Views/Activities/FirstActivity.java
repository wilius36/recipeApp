package com.example.recipeapp.Views.Activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.recipeapp.ViewModels.UserViewModel;
import com.example.recipeapp.Views.Fragments.LogRegUserFragments.FirstFragment;
import com.example.recipeapp.R;

public class FirstActivity extends AppCompatActivity {

    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        if (savedInstanceState == null) {
            FirstFragment f1= new FirstFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.main_frame, f1);
            fragmentTransaction.addToBackStack("FirstFragment");
            fragmentTransaction.commit();

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if(userViewModel.checkIfUserLoggedIn() != null) {
            //openMainActivity();
        }
    }

    //Atidaro main activity
    private void openMainActivity() {
        Intent intent = new Intent(FirstActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    //Paslepia klaviatura
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

}
