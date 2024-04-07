package com.dorontayar_nirtzameret.mygameslist.main.loginFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.dorontayar_nirtzameret.mygameslist.R;

public class loginFragment extends Fragment {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView registerButton;

    private LoginViewModel viewModel;

    public loginFragment() {
    }

    public static loginFragment newInstance() {
        loginFragment fragment = new loginFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        setupUI();
        initViewModels();

    }


    private void initViews(View view){
        usernameEditText = view.findViewById(R.id.editTextUsername);
        passwordEditText = view.findViewById(R.id.editTextPassword);
        loginButton = view.findViewById(R.id.buttonLogin);
        registerButton = view.findViewById(R.id.buttonRegister);
    }
    private void setupUI(){
        loginButton.setOnClickListener(v -> loginUser());
        registerButton.setOnClickListener(v -> navigateToRegisterFragment());
    }
    private void initViewModels(){
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        viewModel.isAuthenticated().observe(getViewLifecycleOwner(), isAuthenticated -> {
            if (isAuthenticated) {
                navigateToMainFragment();
                Log.w("user login", "login success");
            } else {
                Log.w("user login", "login fail");
            }
        });
    }
    private void loginUser() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (!username.isEmpty() && !password.isEmpty()){
            viewModel.login(username, password,getContext());

        } else{
            Toast.makeText(getActivity(), "Email or Password is empty!", Toast.LENGTH_LONG).show();
        }

    }

    private void navigateToMainFragment() {
        Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_mainFragment);
    }

    private void navigateToRegisterFragment() {
        Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_registerFragment);
    }
}

