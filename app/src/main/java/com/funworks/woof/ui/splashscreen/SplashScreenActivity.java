package com.funworks.woof.ui.splashscreen;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

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

        TextView brandingText = findViewById(R.id.intro_text);
        /*Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Bold.ttf");
        brandingText.setTypeface(typeface);*/
        brandingText.setText(getString(R.string.app_name));

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
