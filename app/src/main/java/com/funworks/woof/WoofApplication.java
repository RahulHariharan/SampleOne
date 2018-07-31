package com.funworks.woof;

import android.app.Activity;
import android.app.Application;

import com.funworks.woof.di.DaggerWoofComponent;
import com.funworks.woof.di.WoofComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by rahulhariharan on 29/07/18.
 */

public class WoofApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerWoofComponent.builder().application(this).build().inject(this);

        /*
        WoofApiService.fetch().randomDogs(10)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<RandomDogs>() {
                    @Override
                    public void accept(RandomDogs randomDogs) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });*/

    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }
}
