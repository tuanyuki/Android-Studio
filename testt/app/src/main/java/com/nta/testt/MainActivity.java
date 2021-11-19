package com.nta.testt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnSignIn = (Button) findViewById(R.id.idBTNSignIn_Main);
        Button btnSignUp = (Button) findViewById(R.id.idBTNsignUp_Main);
        btnSignIn.setOnClickListener(view -> startActivity(new Intent(MainActivity.this,SignIn_activity.class)));
        btnSignUp.setOnClickListener(view -> startActivity(new Intent(MainActivity.this,SignUp_Activity.class)));
    }
}