package com.funworks.woof.di;

import android.app.Application;

import com.funworks.woof.WoofApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * Created by rahulhariharan on 30/07/18.
 */
@Singleton
@Component(modules={
        WoofModule.class,
        AndroidInjectionModule.class,
        WoofActivityBuilder.class})
public interface WoofComponent extends AndroidInjector<WoofApplication> {

    @Override
    void inject(WoofApplication instance);

    @Component.Builder
    interface Builder {
        @BindsInstance Builder application(Application application);
        WoofComponent build();
    }
}
