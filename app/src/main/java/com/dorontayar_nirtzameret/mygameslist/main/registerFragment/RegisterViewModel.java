package com.dorontayar_nirtzameret.mygameslist.main.registerFragment;

import android.content.Context;
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

public class RegisterViewModel extends ViewModel {
    private MutableLiveData<Boolean> isRegistered = new MutableLiveData<>();

    // Creating FireBase DatabaseReference to access firebase realtime database
    //private FirebaseDatabase database;
    //private DatabaseReference databaseReference;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://mygamelist-androidproject-default-rtdb.firebaseio.com/");

    public void register(String username, String password, String email, Context context) {
        //database = FirebaseDatabase.getInstance();
        //databaseReference = database.getReference();

        // Checking if the User already exists, if not then register it in the database
        databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(username)){
                    Toast.makeText(context,"User name already exists!",Toast.LENGTH_SHORT).show();
                }
                else {
                    databaseReference.child("users").child(username).child("password").setValue(password);
                    databaseReference.child("users").child(username).child("phone_number").setValue(email);

                    //Toast.makeText(context, "Register successful!", Toast.LENGTH_LONG).show();


                    isRegistered.setValue(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public LiveData<Boolean> isRegistered() {
        return isRegistered;
    }
}
