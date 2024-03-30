package com.dorontayar_nirtzameret.mygameslist.main.mainActivity;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.dorontayar_nirtzameret.mygameslist.R;
import com.dorontayar_nirtzameret.mygameslist.network.ApiManager;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hides the title action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // Initialize ApiManager
        ApiManager.initialize(getApplicationContext());

        // Initialize ViewModel
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // Call preloadData method to fetch data
        mainViewModel.preloadData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Find the NavController associated with the NavHostFragment
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);

        // Observe changes in authentication state and navigate accordingly
        observeAuthenticationState();


    }

    private void observeAuthenticationState() {
        // Check authentication state and navigate accordingly
        if (!isLoggedIn()) {
            navController.navigate(R.id.loginFragment);
        } else {
            navController.navigate(R.id.mainFragment);
        }
    }

    private boolean isLoggedIn() {
        // Check authentication state
        // Return true if user is logged in, false otherwise
        return false; // Replace with your authentication logic
    }
}




