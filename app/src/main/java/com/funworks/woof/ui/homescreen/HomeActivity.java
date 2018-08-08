package com.funworks.woof.ui.homescreen;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.funworks.woof.R;
import com.funworks.woof.ui.mainscreen.MainActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class HomeActivity extends AppCompatActivity {

    @Inject
    HomeScreenViewModel homeScreenViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        HomeScreenViewModel viewModel
                = ViewModelProviders.of(this).get(HomeScreenViewModel.class);
    }

    public void navigateToQuiz(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
