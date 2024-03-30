package com.dorontayar_nirtzameret.mygameslist.ui.filter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dorontayar_nirtzameret.mygameslist.databinding.FragmentFilterBinding;


public class FilterFragment extends Fragment {

    private FragmentFilterBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FilterViewModel filterViewModel =
                new ViewModelProvider(this).get(FilterViewModel.class);

        binding = FragmentFilterBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}