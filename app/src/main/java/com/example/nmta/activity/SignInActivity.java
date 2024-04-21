package com.example.nmta.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nmta.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {
    EditText edt_email_signin,edt_pass_signin, edt_username_signin;
    Button create_account_signin,bt_login_signin;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_layout);



        init();
        create_account_signin.setOnClickListener(v -> {
            String username = edt_username_signin.getText().toString();
            String email = edt_email_signin.getText().toString();
            String pass = edt_pass_signin.getText().toString();
            firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext() ,"Welcome to NmtA", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(SignInActivity.this,LoginActivity.class);
                        startActivity(i);
                    }
                }
            });
        });

        bt_login_signin.setOnClickListener(v -> {
            Intent i = new Intent(SignInActivity.this,LoginActivity.class);
            startActivity(i);
        });
    }

    public void init(){
        edt_pass_signin = (EditText) findViewById(R.id.edt_pass_signin);
        edt_email_signin = (EditText) findViewById(R.id.edt_email_signin);
        edt_username_signin = (EditText) findViewById(R.id.edt_user_signin);
        create_account_signin = (Button) findViewById(R.id.bt_create_signin);
        bt_login_signin = (Button) findViewById(R.id.bt_login_signin);
    }
}
