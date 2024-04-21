package com.example.nmta.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nmta.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    Button bt_login;
    TextView bt_signin;

    EditText edt_user, edt_pass;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);




        bt_login = (Button) findViewById(R.id.bt_login_login);
        bt_signin = (TextView) findViewById(R.id.bt_signin_Login);
        edt_user = (EditText) findViewById(R.id.edt_user_login);
        edt_pass = (EditText) findViewById(R.id.edt_pass_login);

        bt_login.setOnClickListener(view -> {
            String email = edt_user.getText().toString();
            String passWord = edt_pass.getText().toString();
            firebaseAuth.signInWithEmailAndPassword(email,passWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }
            });
       });

            bt_signin.setOnClickListener(view -> {
                Intent i = new Intent(LoginActivity.this, SignInActivity.class);
                startActivity(i);
            });
        }
}

