package com.funworks.woof.ui.scorescreen;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.funworks.woof.utils.SharedPreferencesUtil;

public class ScoreScreenViewModel extends ViewModel {

    public MutableLiveData<Integer> score = new MutableLiveData<>();

}
