package com.funworks.woof.ui.homescreen;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeScreenModule {

    @Provides
    HomeScreenViewModel providesScoreScreenViewModel() {
        return new HomeScreenViewModel();
    }
}
