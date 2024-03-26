package com.dorontayar_nirtzameret.mygameslist.main.mainActivity;

import android.os.Bundle;
import android.view.Window;

import com.dorontayar_nirtzameret.mygameslist.R;
import com.dorontayar_nirtzameret.mygameslist.main.loginFragment.loginFragment;
import com.dorontayar_nirtzameret.mygameslist.main.mainFragment.mainFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.dorontayar_nirtzameret.mygameslist.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hides the title action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


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




