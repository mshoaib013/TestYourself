package com.example.darkshadow.testquiz;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }
    String TAG = "tag";
    public void createAccount(final String email, final String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference uID = database.getReference("U").child(user.getUid()).child("I");
                            uID.setValue(user.getUid());
                            DatabaseReference myRef = database.getReference("U").child(user.getUid()).child("E");
                            myRef.setValue(email);
                            DatabaseReference pass = database.getReference("U").child(user.getUid()).child("P");
                            pass.setValue(password);
                            DatabaseReference bio = database.getReference("U").child(user.getUid()).child("B");
                            bio.setValue("Bio");
                            DatabaseReference name = database.getReference("U").child(user.getUid()).child("N");
                            name.setValue("Name");
                            DatabaseReference rank = database.getReference("U").child(user.getUid()).child("R");
                            rank.setValue(0);
                            DatabaseReference score = database.getReference("U").child(user.getUid()).child("S");
                            score.setValue(0);

                            //go to topic choose after signup
                            final Intent intent = new Intent(Signup.this, TopicChoose.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Signup.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        hide();

        mAuth = FirebaseAuth.getInstance();
        final EditText usernamee = (EditText)findViewById(R.id.signupUsername);
        final EditText passwordd = (EditText)findViewById(R.id.signupPassword);
        final EditText emailorphone = (EditText)findViewById(R.id.signupEmailPhone);
        Button signup = (Button)findViewById(R.id.signupSignupButton);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u = String.valueOf(usernamee.getText());
                String p = String.valueOf(passwordd.getText());
                String e = String.valueOf(emailorphone.getText());

//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference myRef = database.getReference("user").child("1").child("user");
//                myRef.setValue("shoaib");
//
//                DatabaseReference pass = database.getReference("user").child("1").child("pass");
//                pass.setValue("1");
                createAccount(e,p);
            }
        });
    }
}
