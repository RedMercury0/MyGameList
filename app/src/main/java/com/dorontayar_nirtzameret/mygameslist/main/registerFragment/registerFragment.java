package com.dorontayar_nirtzameret.mygameslist.main.registerFragment;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.dorontayar_nirtzameret.mygameslist.R;

public class registerFragment extends Fragment {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText secondPasswordEditText;
    private EditText emailEditText;
    private Button registerButton;
    private TextView haveAccountButton;

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
        haveAccountButton = view.findViewById(R.id.haveAccountButton);

        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        // Setup click listener for register button
        registerButton.setOnClickListener(v -> registerUser());

        haveAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLoginFragment();
            }
        });

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
        if (username.isEmpty() || password.isEmpty() || secondPassword.isEmpty() || email.isEmpty()){
            Toast.makeText(getActivity(), "Please fill all fields", Toast.LENGTH_LONG).show();
        }
        else if(!password.equals(secondPassword)){
            Toast.makeText(getActivity(), "Passwords do not match", Toast.LENGTH_LONG).show();
        }
        else if (password.length() < 6) {
            Toast.makeText(getActivity(), "Password is too short ! should be longer than 6 characters", Toast.LENGTH_SHORT).show();
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(getActivity(), "Invalid email format", Toast.LENGTH_LONG).show();
        }else {

            viewModel.register(username, password, email,getContext());
        }
    }
    private void navigateToMainFragment() {
        Navigation.findNavController(requireView())
                .navigate(R.id.action_registerFragment_to_mainFragment);
    }
    private void navigateToLoginFragment() {
        Navigation.findNavController(requireView())
                .navigate(R.id.action_registerFragment_to_loginFragment);
    }
}

