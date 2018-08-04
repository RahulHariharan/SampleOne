package com.funworks.woof.dataproviders;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.funworks.woof.data.RandomDogs;

import javax.inject.Singleton;

@Singleton
public class RandomDogsProvider {

    private MutableLiveData<RandomDogs> randomDogs = new MutableLiveData<>();

    public LiveData<RandomDogs> getRandomDogs() {
        return randomDogs;
    }

    public void setRandomDogs(RandomDogs randomDogs) {
        this.randomDogs.setValue(randomDogs);
    }
}
