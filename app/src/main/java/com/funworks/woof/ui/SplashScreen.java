package com.funworks.woof.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.funworks.woof.R;
import com.funworks.woof.utils.SharedPreferencesUtil;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;

public class SplashScreen extends AppCompatActivity {

    @Inject
    SharedPreferencesUtil sharedPreferencesUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sharedPreferencesUtil.test();

    }
}
