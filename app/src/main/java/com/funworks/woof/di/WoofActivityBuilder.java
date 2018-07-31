package com.funworks.woof.di;

import com.funworks.woof.ui.MainActivity;
import com.funworks.woof.ui.SplashScreen;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by rahulhariharan on 31/07/18.
 */

@Module
public abstract class WoofActivityBuilder {

    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract SplashScreen bindSplashActivity();
}
