package com.funworks.woof.api;

import com.funworks.woof.data.AllBreeds;
import com.funworks.woof.data.RandomDog;
import com.funworks.woof.data.RandomDogs;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by rahulhariharan on 02/08/18.
 */

public class WoofApiProvider {

    public Observable<AllBreeds> getAllBreeds() {
        return WoofApiService.fetch().allBreeds().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Observable<RandomDog> getRandomDog() {
        return WoofApiService.fetch().randomDog().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Observable<RandomDogs> getRandomDogs(int size) {
        return WoofApiService.fetch().randomDogs(size).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Observable<RandomDogs> dogsByBreed(String breed) {
        return WoofApiService.fetch().dogsByBreed(breed).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Observable<RandomDog> randomDogByBreed(String breed) {
        return WoofApiService.fetch().randomDogByBreed(breed).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Observable<RandomDog> randomDogBySubbreed(String breed, String subbreed) {
        return WoofApiService.fetch().randomDogBySubbreed(breed, subbreed).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
