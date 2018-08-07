package com.funworks.woof.ui.splashscreen;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.funworks.woof.R;
import com.funworks.woof.databinding.ActivitySplashScreenBinding;
import com.funworks.woof.ui.mainscreen.MainActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class SplashScreenActivity extends AppCompatActivity {

    @Inject
    SplashScreenViewModel splashScreenViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        ActivitySplashScreenBinding binding
                = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen);
        binding.setSplashScreenViewModel(splashScreenViewModel);
        splashScreenViewModel.isCountDownComplete.observe(this, isCountDownComplete -> {
            if(isCountDownComplete){
                navigateToMainActivity();
            }
        });

    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
