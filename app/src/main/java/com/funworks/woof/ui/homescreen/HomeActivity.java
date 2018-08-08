package com.funworks.woof.ui.homescreen;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.funworks.woof.R;
import com.funworks.woof.ui.mainscreen.MainActivity;

import java.util.Random;

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
        ((ImageView)findViewById(R.id.image)).setImageDrawable(getDrawable(generateRandomIcon()));
    }

    public void navigateToQuiz(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private int generateRandomIcon() {
        int[] image_ids = {R.drawable.home_icon_1, R.drawable.home_icon_2, R.drawable.home_icon_3};
        Random random = new Random();
        return image_ids[random.nextInt(3)];
    }
}
