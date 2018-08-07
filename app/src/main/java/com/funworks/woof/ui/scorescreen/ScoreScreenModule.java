package com.funworks.woof.ui.scorescreen;

import dagger.Module;
import dagger.Provides;

@Module
public class ScoreScreenModule {

    @Provides
    ScoreScreenViewModel providesScoreScreenViewModel() {
        return new ScoreScreenViewModel();
    }
}
