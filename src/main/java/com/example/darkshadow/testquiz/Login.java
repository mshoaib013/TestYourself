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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;

    public void signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("tag", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();


                            Intent intent=new Intent(Login.this,TopicChoose.class);
                            startActivity(intent);
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("tag", "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //database reference
        final DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("user");
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        //hide actionbar
        hide();


        setContentView(R.layout.activity_login);
        //button edittext reference
        final EditText username = (EditText)findViewById(R.id.loginUsername);
        final EditText password = (EditText)findViewById(R.id.loginPassword);
        final Button login = (Button)findViewById(R.id.loginLoginButton);
        final String TAG = "TAG";

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String u=String.valueOf(username.getText().toString());
                final String p=String.valueOf(password.getText().toString());


                Log.d(TAG,u);
                signIn(u,p);
//                mDatabase.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
//                            User user1=dataSnapshot1.getValue(User.class);
//                            String uf = user1.getUsername().toString();
//                            String pf = user1.getPassword().toString();
//                            Log.d(TAG, "onDataChange: xxu "+u);
//                            Log.d(TAG, "onDataChange: xxp "+p);
//                            Log.d(TAG, "onDataChange: xxuf "+user1.getUsername());
//                            Log.d(TAG, "onDataChange: xxpf "+user1.getPassword());
//                            signIn(u,p);
//                            if (uf.equals(u) && pf.equals(p)){
//                                Log.d(TAG, "onDataChange: xxuf "+user1.getUsername());
//                                Log.d(TAG, "onDataChange: xxpf "+user1.getPassword());
//                                final Intent intent = new Intent(Login.this, TopicChoose.class);
//                                startActivity(intent);
//                            }
//
//                            //if(user1.getPassword().equals())h
////                            for(DataSnapshot dataSnapshot2:dataSnapshot1.getChildren()){
////                                Log.d(TAG, "onDataChange: xx" + dataSnapshot2);
////                                if(dataSnapshot2.getKey().equals("password")){
////                                    if(p.equals(dataSnapshot2.getValue(String.class))){
////                                        Log.d(TAG, "onDataChange: xx1"+ "ok");
////                                    }
////                                    else{
////                                        Log.d(TAG, "onDataChange: xx1"+ "not ok");
////                                    }
////                                }
////
////
////                                else if(dataSnapshot2.getKey().equals("username")){
////                                    if(u.equals(dataSnapshot2.getValue(String.class))){
////                                        Log.d(TAG, "onDataChange: xx2"+ "ok");
////                                    }
////                                    else{
////                                        Log.d(TAG, "onDataChange: xx2"+ "not ok");
////                                    }
////                                }
////                            }
//
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });

            }
        });
    }
    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

}
