package com.dorontayar_nirtzameret.mygameslist.main.mainFragment;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.dorontayar_nirtzameret.mygameslist.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class mainFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // Setting up the bottom navigation bar with the home,filter,bookmarks fragments

        BottomNavigationView navView = view.findViewById(R.id.nav_view);
        navView.setItemIconTintList(null);
        navView.setItemTextColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));

        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_fragment_main);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_filter, R.id.navigation_bookmarks)
                .build();

        NavController parentNavController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_fragment_main);

        NavigationUI.setupActionBarWithNavController((AppCompatActivity) requireActivity(), parentNavController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, parentNavController);
    }
}


