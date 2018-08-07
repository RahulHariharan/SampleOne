package com.funworks.woof.ui.mainscreen;

import com.funworks.woof.api.WoofApiProvider;
import com.funworks.woof.dataproviders.AllBreedsProvider;
import com.funworks.woof.dataproviders.RandomDogProvider;
import com.funworks.woof.ui.splashscreen.SplashScreenViewModel;
import com.funworks.woof.utils.SharedPreferencesUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainScreenModule {

    @Provides
    MainViewModel providesMainViewModel(WoofApiProvider woofApiProvider,
                                        RandomDogProvider randomDogProvider,
                                        SharedPreferencesUtil sharedPreferencesUtil) {
        return new MainViewModel(woofApiProvider,
                randomDogProvider,
                sharedPreferencesUtil);
    }
}
