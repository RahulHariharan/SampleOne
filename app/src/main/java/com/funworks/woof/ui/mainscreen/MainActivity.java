package com.funworks.woof.ui.mainscreen;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import com.funworks.woof.ui.homescreen.HomeActivity;
import com.funworks.woof.utils.UIUtil;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

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
        getSupportActionBar().setTitle("");
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

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
    public void onDestroy() {
        super.onDestroy();
        removeFragment();
    }

    /*@Override
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
    }*/

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

            int width = getActivity().getWindow().getDecorView().getWidth();
            ImageView imageView = rootView.findViewById(R.id.image_view);
            picasso = Picasso.get();
            if(width != 0) {
                UIUtil util = ((MainActivity)getActivity()).getMainViewModel().getUiUtil();
                picasso.load(getArguments().getString(URL))
                        .transform(new RoundedCornersTransformation(util.dpToPx(15),0))
                        .resize(width, width)
                        .centerCrop()
                        .into(imageView, new Callback() {
                            @Override
                            public void onSuccess() {
                                updateUI(rootView);
                            }

                            @Override
                            public void onError(Exception e) {

                            }
                        });
            }

            return rootView;
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            if(picasso != null)
                picasso.invalidate(URL);
        }

        private void updateUI(View rootView) {
            MainViewModel viewModel = ((MainActivity)getActivity()).getMainViewModel();

            Button optionOne = rootView.findViewById(R.id.option_one);
            viewModel.optionOne.observe((MainActivity)getActivity(), option -> {
                optionOne.setVisibility(View.VISIBLE);
                optionOne.setText(option);
            });

            Button optionTwo = rootView.findViewById(R.id.option_two);
            viewModel.optionTwo.observe((MainActivity)getActivity(), option -> {
                optionTwo.setVisibility(View.VISIBLE);
                optionTwo.setText(option);
            });
        }
    }

    public void updateScoreAndFetchBreed(View view) {
        if (mainViewModel.getCorrectBreed().equals(((Button)view).getText().toString())) {
            mainViewModel.incrementScore().observe(this, score -> {});
            mainViewModel.fetchBreed();
        } else {
            int highScore = mainViewModel.getSharedPreferencesUtil().getHighScore(this);
            int currentScore = mainViewModel.score.getValue();
            boolean isNewHighScore = currentScore > highScore;
            if(isNewHighScore)
                mainViewModel.getSharedPreferencesUtil().saveHighScore(this, currentScore);
            showScoreDialog(isNewHighScore);
        }

    }

    private void showScoreDialog(boolean isNewHighScore) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.activity_score);
        AlertDialog dialog = builder.create();
        dialog.show();
        mainViewModel.score.observe(this, currentScore -> {
            dialog.findViewById(R.id.ok_button).setVisibility(currentScore == 0 ? View.GONE : View.VISIBLE);
            dialog.findViewById(R.id.cheer_text).setVisibility(currentScore == 0 ? View.GONE : View.VISIBLE);
            dialog.findViewById(R.id.shimmer_view_container).setVisibility(currentScore == 0 ? View.GONE : View.VISIBLE);
            ((TextView)dialog.findViewById(R.id.current_score)).setText(mainViewModel.score.getValue().toString());

            dialog.findViewById(R.id.dog_image).setVisibility(currentScore == 0 ? View.VISIBLE : View.GONE);
            dialog.findViewById(R.id.retry_button).setVisibility(currentScore == 0 ? View.VISIBLE : View.GONE);
        });
        if(isNewHighScore)
            ((TextView)(dialog.findViewById(R.id.cheer_text)).findViewById(R.id.cheer_text)).setText("You have a new high score!");
        dialog.findViewById(R.id.ok_button).setOnClickListener(view -> navigateToScoreScreen(mainViewModel.score.getValue()));
        dialog.findViewById(R.id.retry_button).setOnClickListener(view -> {
            dialog.dismiss();
            mainViewModel.fetchBreed();});
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    private void navigateToScoreScreen(int currentScore) {
        Intent intent = new Intent(this, HomeActivity.class);
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

    private void removeFragment() {
        FragmentManager manager = getFragmentManager();
        PlaceholderFragment fragment = (PlaceholderFragment) manager.findFragmentByTag(QUIZ_FRAGMENT_TAG);
        if(fragment != null) {
            manager.beginTransaction().remove(fragment);
        }
    }
}
