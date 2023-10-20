package com.example.fitguide;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

import java.util.HashMap;
import java.util.Map;

public class MainActivityLogin extends AppCompatActivity {
    Button create_act,sign_button;
    TextInputEditText username,password;
    TextInputLayout parent_password;
    FirebaseAuth firebaseAuth ;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        create_act = findViewById(R.id.create_act_btn);
        parent_password = findViewById(R.id.parent_password_sign);
        sign_button =  findViewById(R.id.login_btn);
        username = findViewById(R.id.username_sign);
        password = findViewById(R.id.pass_sign);
        firebaseAuth = FirebaseAuth.getInstance();

        sign_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().isEmpty() || username.getText().toString().equals("")){
                    username.setError("*Fill");
                }else{
                    if(password.getText().toString().length()<6){
                        parent_password.setHelperText("*must be greater than 5");
                    }else{
                        firebaseAuth.signInWithEmailAndPassword(username.getText().toString(),password.getText().toString())
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        Toast.makeText(MainActivityLogin.this,"Successfully Login",Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(MainActivityLogin.this,MainActivity2.class));
                                    }
                                });
                    }
                }



            }
        });
        create_act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityLogin.this, MainActivityCreateAccount.class));
            }
        });


    }
}
