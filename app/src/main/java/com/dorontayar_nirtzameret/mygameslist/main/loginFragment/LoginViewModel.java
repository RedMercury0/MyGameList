package com.dorontayar_nirtzameret.mygameslist.main.loginFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginViewModel extends ViewModel {
    // LiveData for authentication result
    private MutableLiveData<Boolean> isAuthenticated = new MutableLiveData<>();
    private MutableLiveData<String> loggedUsername = new MutableLiveData<>();

    // Creating FireBase DatabaseReference to access firebase realtime database
    //private FirebaseDatabase database;
    //private DatabaseReference databaseReference;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://mygamelist-androidproject-default-rtdb.firebaseio.com/");


    // Method to perform login
    public void login(String username, String password, Context context) {
        //database = FirebaseDatabase.getInstance();
        //databaseReference = database.getReference();

        // Checking if the User already exists, if not then register it in the database
        databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // checks if the user name exist in the database
                if(snapshot.hasChild(username)){

                    // the user exist in the database
                    // checks if the password are correct

                    final String getUserPassword = snapshot.child(username).child("password").getValue(String.class);
                    if(getUserPassword.equals(password)){
                        //Toast.makeText(context, "Login in", Toast.LENGTH_LONG).show();
                        loggedUsername.setValue(username);
                        isAuthenticated.setValue(true);
                        Toast.makeText(context, "Welcome "+ username, Toast.LENGTH_LONG).show();
                        saveLoggedInUser(username,context);

                    }
                    else {
                        Toast.makeText(context, "Wrong password", Toast.LENGTH_LONG).show();
                    }


                }
                else {
                    Toast.makeText(context,"User does not exists, please register!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public LiveData<Boolean> isAuthenticated() {
        return isAuthenticated;
    }
    public LiveData<String> getLoggedUsername() {
        return loggedUsername;
    }
    private void saveLoggedInUser(String username, Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE).edit();
        editor.putString("loggedInUser", username);
        editor.apply();
    }


}
