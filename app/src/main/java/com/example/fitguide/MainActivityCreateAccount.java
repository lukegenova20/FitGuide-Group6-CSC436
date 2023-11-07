package com.example.fitguide;
import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fitguide.Workout_Classes.WorkoutRoutine;
import com.example.fitguide.Workout_Classes.WorkoutRoutineList;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
//test change for github version control guide

public class MainActivityCreateAccount extends AppCompatActivity {
    Button create_act ;
    TextInputEditText f_name,l_name,weight,username,password;
    FirebaseAuth firebaseAuth;
    TextInputLayout parent_password;
    FirebaseFirestore firebaseFirestore;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_create_account);
        f_name = findViewById(R.id.first_name);
        l_name = findViewById(R.id.last_name);
        weight = findViewById(R.id.weight);
        username = findViewById(R.id.user_name);
        parent_password  = findViewById(R.id.parent_password);
        password = findViewById(R.id.password);
        create_act = findViewById(R.id.create_btn_2);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityCreateAccount.this);
        builder.setIcon(R.drawable.baseline_error_24);
        builder.setTitle(" ");
        builder.setCancelable(true);
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
        create_act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(f_name.getText().toString().isEmpty()){
                    f_name.setError("*Fill");
                }else{
                    if(l_name.getText().toString().isEmpty()){
                        l_name.setError("*Fill");
                    }else{
                        if(weight.getText().toString().isEmpty()){
                            weight.setError("*Fill");
                        }else{
                            if(username.getText().toString().isEmpty()){
                                username.setError("*Fill");
                            }else{
                                if(password.getText().toString().isEmpty()){
                                    parent_password.setHelperText("password should not be empty");
                                }else{
                                    if(password.getText().toString().length()>5 && password.getText().toString().length()<=16){
                                        firebaseAuth.createUserWithEmailAndPassword(
                                                username.getText().toString(),password.getText().toString()
                                        ).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                            @Override
                                            public void onSuccess(AuthResult authResult) {

                                                // Create document for personal info.
                                                Map<String, Object> datas = new HashMap<>();
                                                datas.put("First Name",f_name.getText().toString());
                                                datas.put("Last Name",l_name.getText().toString());
                                                datas.put("Weight(LBS)",weight.getText().toString());
                                                firebaseFirestore.collection(authResult.getUser().getUid()).
                                                        document("Personal_info").set(datas).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void unused) {
                                                                startActivity(new Intent(MainActivityCreateAccount.this,MainActivity2.class));
                                                            }
                                                        });

                                                // Create document for workout info.
                                                WorkoutRoutineList routineList = new WorkoutRoutineList(null);
                                                firebaseFirestore.collection(authResult.getUser().getUid()).
                                                        document("Workout_Routines").set(routineList).
                                                        addOnFailureListener(new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {
                                                                Log.w(TAG, "Error writing document", e);
                                                            }
                                                        });

                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                builder.setMessage(e.getMessage());
                                                AlertDialog alertDialog = builder.create();
                                                alertDialog.show();
                                            }
                                        });
                                    }else if(password.getText().toString().length()<=5){
                                        parent_password.setHelperText("password must be greater than 5");
                                    }else{
                                        parent_password.setHelperText("password must be lesser than 17");
                                    }
                                }
                            }
                        }
                    }
                }

            }
        });
    }
}

