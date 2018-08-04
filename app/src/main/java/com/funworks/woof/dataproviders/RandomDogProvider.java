package com.funworks.woof.dataproviders;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.funworks.woof.data.RandomDog;

import javax.inject.Singleton;

@Singleton
public class RandomDogProvider {

    private MutableLiveData<RandomDog> randomDog;

    public LiveData<RandomDog> getRandomDog() {
        return randomDog;
    }

    public void setRandomDog(RandomDog randomDog) {
        this.randomDog.setValue(randomDog);
    }
}
