package com.example.TruckParkingApp.ui.contribute;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ContributeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ContributeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is contribute fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}