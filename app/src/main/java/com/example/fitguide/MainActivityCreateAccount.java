package com.example.fitguide;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivityCreateAccount extends AppCompatActivity {
    Button create_act ;
    TextInputEditText f_name,l_name,weight,username,password;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        f_name = findViewById(R.id.first_name);
        l_name = findViewById(R.id.last_name);
        weight = findViewById(R.id.weight);
        username = findViewById(R.id.user_name);
        password = findViewById(R.id.password);
        create_act = findViewById(R.id.create_btn_2);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        create_act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("arun","Sds");

                firebaseAuth.createUserWithEmailAndPassword(
                        username.getText().toString(),password.getText().toString()
                ).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Map<String, Object> datas = new HashMap<>();
                        datas.put("First Name",f_name.getText().toString());
                        datas.put("Last Name",l_name.getText().toString());
                        datas.put("Weight(LBS)",weight.getText().toString());
                        firebaseFirestore.collection(authResult.getUser().getUid()).
                                document("Personal_info").set(datas).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(MainActivityCreateAccount.this,"Successfully created",Toast.LENGTH_LONG).show();
                                    }
                                });

                    }
                });

            }
        });
    }
}



