package com.dorontayar_nirtzameret.mygameslist.main.loginFragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
    // LiveData for authentication result
    private MutableLiveData<Boolean> isAuthenticated = new MutableLiveData<>();


    // Method to perform login
    public void login(String username, String password) {
        // Implement authentication logic here


        isAuthenticated.setValue(true);
    }

    public LiveData<Boolean> isAuthenticated() {
        return isAuthenticated;
    }
}
