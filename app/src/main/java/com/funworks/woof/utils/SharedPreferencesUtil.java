package com.funworks.woof.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.funworks.woof.R;

import javax.inject.Singleton;

import dagger.Module;
import io.reactivex.Observable;

/**
 * Created by rahulhariharan on 30/07/18.
 */

public class SharedPreferencesUtil {

    public void saveHighScore(Activity activity, int highScore) {
        SharedPreferences.Editor editor = activity.getPreferences(Context.MODE_PRIVATE).edit();
        editor.putInt(activity.getResources().getString(R.string.saved_high_score_key), highScore);
        editor.apply();
    }

    public int getHighScore(Activity activity) {
        return activity
                .getPreferences(Context.MODE_PRIVATE)
                .getInt(activity.getResources().getString(R.string.saved_high_score_key),
                        activity.getResources().getInteger(R.integer.saved_high_score_default_key));
    }
}
