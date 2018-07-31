package com.funworks.woof.di;

import android.app.Application;
import android.content.Context;

import com.funworks.woof.utils.SharedPreferencesUtil;

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

    @Binds
    abstract Context providesContext(Application application);
}
