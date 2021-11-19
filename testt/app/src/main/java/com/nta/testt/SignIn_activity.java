package com.nta.testt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nta.testt.common.Common;
import com.nta.testt.common.User;

public class SignIn_activity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        EditText edtPhone       = (EditText) findViewById(R.id.idEDTphome_Login);
        EditText edtPassword    = (EditText) findViewById(R.id.idEDTpassword_Login);
        Button btnLogin         = (Button) findViewById(R.id.idBTNsignIn_Login);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("User");
        btnLogin.setOnClickListener(view -> {
            if(edtPhone.getText().toString().isEmpty() || edtPassword.getText().toString().isEmpty()){
                Toast.makeText(SignIn_activity.this, "please enough input data", Toast.LENGTH_SHORT).show();
            }
            else{
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.child(edtPhone.getText().toString()).exists()) {
                            User user = snapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            if (edtPassword.getText().toString().equals(user.getPassword())) {
                                Common.CurrentUser = user;
                                Log.d("DDD", user.getUsername() + "  " + user.getPassword());
                                startActivity(new Intent(SignIn_activity.this, home.class));
                            } else {
                                Toast.makeText(SignIn_activity.this, "ERROR password", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(SignIn_activity.this, "your phone number isn't register", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });
    }
}