package com.funworks.woof.ui.splashscreen;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.funworks.woof.api.WoofApiProvider;
import com.funworks.woof.data.AllBreeds;
import com.funworks.woof.dataproviders.AllBreedsProvider;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by rahulhariharan on 01/08/18.
 */

public class SplashScreenViewModel extends ViewModel {

    private final Observable<AllBreeds> allBreedsObservable;
    private final AllBreedsProvider allBreedsProvider;

    @Inject
    public SplashScreenViewModel(WoofApiProvider woofApiProvider,
                                 AllBreedsProvider allBreedsProvider) {

        this.allBreedsObservable = woofApiProvider.getAllBreeds();
        this.allBreedsProvider = allBreedsProvider;
    }

    @Override
    public void onCleared() {
        allBreedsObservable.unsubscribeOn(Schedulers.io());
    }

    public LiveData<AllBreeds> getAllBreeds() {
        return allBreedsProvider.getAllBreeds();
    }

    public void fetchAllBreeds() {
        allBreedsObservable.subscribe(this::onSuccess, this::onFailure);
    }

    private void onSuccess(AllBreeds allBreeds) {
        allBreedsProvider.setAllBreeds(allBreeds);
    }

    private void onFailure(Throwable throwable) {
        //TODO - add error handling
        throwable.printStackTrace();
    }

}
