package com.funworks.woof.dataproviders;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.funworks.woof.data.AllBreeds;

import javax.inject.Singleton;

@Singleton
public class AllBreedsProvider {

    private MutableLiveData<AllBreeds> allBreeds = new MutableLiveData<>();

    public void setAllBreeds(AllBreeds allBreeds) {
        this.allBreeds.setValue(allBreeds);
    }

    public LiveData<AllBreeds> getAllBreeds() {
        return allBreeds;
    }
}
