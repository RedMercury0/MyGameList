package com.dorontayar_nirtzameret.mygameslist.main.registerFragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.dorontayar_nirtzameret.mygameslist.R;

public class registerFragment extends Fragment {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText secondPasswordEditText;
    private EditText emailEditText;
    private Button registerButton;

    private RegisterViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        // Initialize UI components
        usernameEditText = view.findViewById(R.id.editTextUsername);
        passwordEditText = view.findViewById(R.id.editTextPassword);
        secondPasswordEditText = view.findViewById(R.id.editTextSecondPassword);
        emailEditText = view.findViewById(R.id.editTextEmail);
        registerButton = view.findViewById(R.id.buttonRegister);

        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        // Setup click listener for register button
        registerButton.setOnClickListener(v -> registerUser());

        // Observe register result
        viewModel.isRegistered().observe(getViewLifecycleOwner(), isRegistered -> {
            if (isRegistered) {
                navigateToMainFragment();
            } else {
                // Handle unsuccessful register
            }
        });

        return view;
    }

    private void registerUser() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String secondPassword = secondPasswordEditText.getText().toString();
        String email = emailEditText.getText().toString();
        viewModel.register(username, password, secondPassword, email);
    }
    private void navigateToMainFragment() {
        Navigation.findNavController(requireView())
                .navigate(R.id.action_registerFragment_to_mainFragment);
    }
}

