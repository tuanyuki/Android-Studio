package com.nta.testt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nta.testt.common.User;

public class SignUp_Activity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        EditText edtPhone       = (EditText) findViewById(R.id.idEDTphome_Register);
        EditText edtUsername    = (EditText) findViewById(R.id.idEDTusername_Register);
        EditText edtPassword    = (EditText) findViewById(R.id.idEDTpassword_Register);
        Button btnSignUp        = (Button) findViewById(R.id.idBTNsignUp_Signup);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("User");

        btnSignUp.setOnClickListener(view -> {
            if(edtPhone.getText().toString().isEmpty()||edtUsername.getText().toString().isEmpty()||
            edtPassword.getText().toString().isEmpty()){
                Toast.makeText(SignUp_Activity.this, "please input enough your data", Toast.LENGTH_SHORT).show();
            }else{
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.child(edtPhone.getText().toString()).exists()){
                            Toast.makeText(SignUp_Activity.this, "your phone number is exits", Toast.LENGTH_SHORT).show();
                        }else{
                            User user = new User(edtUsername.getText().toString(),edtPassword.getText().toString());
                            reference.child(edtPhone.getText().toString()).setValue(user);
                            finish();
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