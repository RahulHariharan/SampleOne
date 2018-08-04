package com.funworks.woof.di;

import android.app.Application;
import android.content.Context;

import com.funworks.woof.api.WoofApiProvider;
import com.funworks.woof.data.RandomDog;
import com.funworks.woof.dataproviders.AllBreedsProvider;
import com.funworks.woof.dataproviders.RandomDogProvider;
import com.funworks.woof.dataproviders.RandomDogsProvider;
import com.funworks.woof.utils.SharedPreferencesUtil;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by rahulhariharan on 30/07/18.
 */

@Module
public abstract class WoofModule {

    @Provides
    public static SharedPreferencesUtil providesSharedPreferencesUtil() {
        return new SharedPreferencesUtil();
    }

    @Provides
    public static WoofApiProvider providesWoofApiProvider() {
        return new WoofApiProvider();
    }

    @Provides
    @Singleton
    static AllBreedsProvider providesAllBreeds() {
        return new AllBreedsProvider();
    }

    @Provides
    @Singleton
    static RandomDogProvider providesRandomDog() {
        return new RandomDogProvider();
    }

    @Provides
    @Singleton
    static RandomDogsProvider providesRandomDogs() {
        return new RandomDogsProvider();
    }

    @Binds
    abstract Context providesContext(Application application);
}
