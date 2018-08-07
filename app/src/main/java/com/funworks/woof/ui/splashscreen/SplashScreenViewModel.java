package com.funworks.woof.ui.splashscreen;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.CountDownTimer;
import android.util.Log;

import com.funworks.woof.api.WoofApiProvider;
import com.funworks.woof.data.AllBreeds;
import com.funworks.woof.dataproviders.AllBreedsProvider;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by rahulhariharan on 01/08/18.
 */

public class SplashScreenViewModel extends ViewModel {

    public final MutableLiveData<Boolean> isCountDownComplete = new MutableLiveData<>();

    public SplashScreenViewModel() {
        this.isCountDownComplete.setValue(false);
        beginCountDown();
    }

    private void beginCountDown() {
        new CountDownTimer(5000, 5000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                isCountDownComplete.setValue(true);
            }
        }.start();
    }
}
