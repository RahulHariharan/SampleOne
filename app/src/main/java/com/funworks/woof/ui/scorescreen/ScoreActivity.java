package com.funworks.woof.ui.scorescreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.funworks.woof.R;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class ScoreActivity extends AppCompatActivity {

    @Inject ScoreScreenViewModel scoreScreenViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        getSupportActionBar().hide();
        int score = getIntent().getIntExtra(getString(R.string.current_score_key),0);
        scoreScreenViewModel.score.setValue(score);
        scoreScreenViewModel.score.observe(this, currentScore ->
            ((TextView)findViewById(R.id.current_score)).setText(currentScore.toString()));
    }
}
