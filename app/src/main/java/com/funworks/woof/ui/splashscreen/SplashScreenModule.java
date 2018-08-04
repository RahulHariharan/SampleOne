package com.funworks.woof.ui.splashscreen;

import com.funworks.woof.api.WoofApiProvider;
import com.funworks.woof.dataproviders.AllBreedsProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rahulhariharan on 01/08/18.
 */

@Module
public class SplashScreenModule {

    @Provides
    SplashScreenViewModel providesSplashScreenViewModel(WoofApiProvider woofApiProvider,
                                                        AllBreedsProvider allBreedsProvider) {
        return new SplashScreenViewModel(woofApiProvider, allBreedsProvider);
    }
}
