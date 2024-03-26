package com.dorontayar_nirtzameret.mygameslist.main.registerFragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegisterViewModel extends ViewModel {
    private MutableLiveData<Boolean> isRegistered = new MutableLiveData<>();

    public void register(String username, String password, String secondPassword, String email) {
        // Implement registration logic here

        isRegistered.setValue(true);
    }

    public LiveData<Boolean> isRegistered() {
        return isRegistered;
    }
}
