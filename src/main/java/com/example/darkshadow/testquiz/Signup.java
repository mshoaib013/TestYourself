package com.example.darkshadow.testquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        final EditText username = (EditText)findViewById(R.id.signupUsername);
        final EditText password = (EditText)findViewById(R.id.signupPassword);
        final EditText emailorphone = (EditText)findViewById(R.id.signupEmailPhone);
        Button signup = (Button)findViewById(R.id.signupSignupButton);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u = String.valueOf(username.getText());
                String p = String.valueOf(password.getText());
                String e = String.valueOf(emailorphone.getText());

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("user").child("1").child("user");
                myRef.setValue("shoaib");

                DatabaseReference pass = database.getReference("user").child("1").child("pass");
                pass.setValue("1");

            }
        });
    }
}
