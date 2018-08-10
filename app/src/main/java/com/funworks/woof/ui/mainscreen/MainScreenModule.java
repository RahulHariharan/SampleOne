package com.funworks.woof.ui.mainscreen;

import com.funworks.woof.api.WoofApiProvider;
import com.funworks.woof.dataproviders.RandomDogProvider;
import com.funworks.woof.utils.ConnectivityUtil;
import com.funworks.woof.utils.SharedPreferencesUtil;
import com.funworks.woof.utils.UIUtil;

import dagger.Module;
import dagger.Provides;

@Module
public class MainScreenModule {

    @Provides
    MainViewModel providesMainViewModel(WoofApiProvider woofApiProvider,
                                        RandomDogProvider randomDogProvider,
                                        SharedPreferencesUtil sharedPreferencesUtil,
                                        UIUtil uiUtil,
                                        ConnectivityUtil connectivityUtil){

        return new MainViewModel(woofApiProvider,
                randomDogProvider,
                sharedPreferencesUtil,
                uiUtil,connectivityUtil);
    }
}
