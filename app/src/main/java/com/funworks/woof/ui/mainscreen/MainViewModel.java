package com.funworks.woof.ui.mainscreen;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.util.Log;

import com.funworks.woof.api.WoofApiProvider;
import com.funworks.woof.data.RandomDog;
import com.funworks.woof.dataproviders.Constants;
import com.funworks.woof.dataproviders.RandomDogProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {

    public final MutableLiveData<String> optionOne = new MutableLiveData<>();
    public final MutableLiveData<String> optionTwo = new MutableLiveData<>();

    private final WoofApiProvider woofApiProvider;
    private final RandomDogProvider randomDogProvider;
    private final List<String> options = new ArrayList<>();
    private final int NUMBER_OF_OPTIONS = 2;

    private String correctOption;
    private Observable<RandomDog> randomDogObservable;

    public MainViewModel(WoofApiProvider woofApiProvider, RandomDogProvider randomDogProvider) {
        this.woofApiProvider = woofApiProvider;
        this.randomDogProvider = randomDogProvider;
    }

    @Override
    public void onCleared() {
        if(randomDogObservable != null)
            randomDogObservable.unsubscribeOn(Schedulers.io());
    }

    public void fetchBreed() {
        randomDogObservable = woofApiProvider.randomDogByBreed(generateBreedWithOptions());
        randomDogObservable.subscribe(this::onSuccess, this::onFailure);
    }

    public String getCorrectBreed() {
        return this.correctOption;
    }

    public RandomDogProvider getRandomDogProvider() {
        return randomDogProvider;
    }

    private String generateBreedWithOptions() {
        Collections.shuffle(Constants.BREEDS);
        options.clear();
        for(int index=0; index < NUMBER_OF_OPTIONS; index++) {
            String breed = Constants.BREEDS.get(index);
            if(index == 0) {
                correctOption = breed;
            }
            options.add(breed);
        }
        return correctOption;
    }

    private void assignBreedsToAnswers() {
        Collections.shuffle(options);
        optionOne.setValue(options.get(0));
        optionTwo.setValue(options.get(1));
    }

    private void onSuccess(RandomDog randomDog) {
        randomDogProvider.setRandomDog(randomDog);
        assignBreedsToAnswers();
    }

    private void onFailure(Throwable throwable) {
        fetchBreed();
    }
}
