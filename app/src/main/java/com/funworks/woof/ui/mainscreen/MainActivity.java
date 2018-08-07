package com.funworks.woof.ui.mainscreen;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.funworks.woof.R;
import com.funworks.woof.databinding.ActivityMainBinding;
import com.funworks.woof.ui.scorescreen.ScoreActivity;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {

    @Inject MainViewModel mainViewModel;

    private static final String QUIZ_FRAGMENT_TAG = "QUIZ_FRAGMENT_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setMainViewModel(mainViewModel);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainViewModel.fetchBreed();
        mainViewModel.getRandomDogProvider().getRandomDog().observe(this, randomDog -> {
            if(randomDog.status.equalsIgnoreCase("success")) {
                loadFragment(randomDog.message);
            } else {
                // TODO: error handling
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {
        private static final String URL = "URL";
        private Picasso picasso;

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(String url) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putString(URL, url);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            MainViewModel viewModel = ((MainActivity)getActivity()).getMainViewModel();

            ImageView imageView = rootView.findViewById(R.id.image_view);
            picasso = Picasso.get();
            picasso
                    .load(getArguments().getString(URL))
                    .resizeDimen(R.dimen.image_width, R.dimen.image_height)
                    .centerCrop()
                    .into(imageView);

            Button optionOne = rootView.findViewById(R.id.option_one);
            viewModel.optionOne.observe((MainActivity)getActivity(), option -> optionOne.setText(option));

            Button optionTwo = rootView.findViewById(R.id.option_two);
            viewModel.optionTwo.observe((MainActivity)getActivity(), option -> optionTwo.setText(option));

            return rootView;
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            if(picasso != null)
                picasso.invalidate(URL);
        }
    }

    public void updateScoreAndFetchBreed(View view) {
        if (mainViewModel.getCorrectBreed().equals(((Button)view).getText().toString())) {
            mainViewModel.incrementScore().observe(this, score -> {});
            mainViewModel.fetchBreed();
        } else {
            int highScore = mainViewModel.getSharedPreferencesUtil().getHighScore(this);
            int currentScore = mainViewModel.score.getValue();

            if(currentScore > highScore)
                mainViewModel.getSharedPreferencesUtil().saveHighScore(this, mainViewModel.score.getValue());
            showScoreDialog();
            //navigateToScoreScreen(currentScore);
        }

    }

    private void showScoreDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.retry_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
        //((TextView)dialog.findViewById(R.id.current_score)).setText(mainViewModel.score.getValue().toString());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    private void navigateToScoreScreen(int currentScore) {
        Intent intent = new Intent(this, ScoreActivity.class);
        intent.putExtra(getResources().getString(R.string.current_score_key), currentScore);
        startActivity(intent);
        finish();
    }

    private void loadFragment(String url) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, PlaceholderFragment.newInstance(url), QUIZ_FRAGMENT_TAG);
        transaction.commit();
    }

    private MainViewModel getMainViewModel() {
        return mainViewModel;
    }
}
