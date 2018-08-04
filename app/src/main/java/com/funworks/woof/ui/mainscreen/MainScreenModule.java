package com.funworks.woof.ui.mainscreen;

import com.funworks.woof.api.WoofApiProvider;
import com.funworks.woof.dataproviders.AllBreedsProvider;
import com.funworks.woof.ui.splashscreen.SplashScreenViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class MainScreenModule {

    @Provides
    MainViewModel providesMainViewModel(WoofApiProvider woofApiProvider,
                                                        AllBreedsProvider allBreedsProvider) {
        return new MainViewModel(woofApiProvider, allBreedsProvider);
    }
}
