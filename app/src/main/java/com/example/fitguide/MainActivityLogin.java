package com.example.fitguide;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fitguide.Workout_Classes.Exercise;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivityLogin extends AppCompatActivity {
    Button create_act,sign_button;
    TextInputEditText username,password;
    TextInputLayout parent_password,parent_username;
    FirebaseAuth firebaseAuth ;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        create_act = findViewById(R.id.create_act_btn);
        parent_password = findViewById(R.id.parent_password_sign);
        parent_username = findViewById(R.id.parent_username_sign);
        sign_button =  findViewById(R.id.login_btn);
        username = findViewById(R.id.username_sign);
        password = findViewById(R.id.pass_sign);
        firebaseAuth = FirebaseAuth.getInstance();
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                parent_password.setHelperText("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //createExerciseDatabase();

        sign_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().isEmpty() || username.getText().toString().equals("")){
                    username.setError("*Fill");
                    parent_password.setHelperText("");
                }else{
                    if(password.getText().toString().isEmpty()){
                        parent_password.setHelperText("*Fill");
                    }else{
                        firebaseAuth.signInWithEmailAndPassword(username.getText().toString(),password.getText().toString())
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        startActivity(new Intent(MainActivityLogin.this,MainActivity2.class));
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityLogin.this);
                                        builder.setIcon(R.drawable.baseline_error_24);
                                        builder.setTitle(" ");
                                        builder.setCancelable(true);
                                        builder.setMessage("Username/Password is Invalid");
                                        AlertDialog alertDialog = builder.create();
                                        alertDialog.show();
                                    }
                                });
                    }
                }



            }
        });
        create_act.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivityLogin.this, MainActivityCreateAccount.class));
                    }
                }
        );


    }

    /*

    // utility function to add data into database programmatically
    private void createExerciseDatabase(){
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

        DocumentReference docs = firebaseFirestore.collection("Exercises").document("Push");
        Map<String, Object> data = new HashMap<String, Object>();
        List<Exercise> list = new ArrayList<Exercise>();
        Exercise exercise = new Exercise("Overhead Press");
        list.add(exercise);
        Exercise exercise1 = new Exercise("Chest Fly");
        list.add(exercise1);
        data.put("Exercises", list);
        docs.set(data);

    }

     */
}
