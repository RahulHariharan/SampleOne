package com.funworks.woof.di;

import com.funworks.woof.ui.mainscreen.MainActivity;
import com.funworks.woof.ui.mainscreen.MainScreenModule;
import com.funworks.woof.ui.mainscreen.MainViewModel;
import com.funworks.woof.ui.splashscreen.SplashScreenActivity;
import com.funworks.woof.ui.splashscreen.SplashScreenModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by rahulhariharan on 31/07/18.
 */

@Module
public abstract class WoofActivityBuilder {

    @ContributesAndroidInjector(modules= {MainScreenModule.class})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = {SplashScreenModule.class})
    abstract SplashScreenActivity bindSplashActivity();
}
