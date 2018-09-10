package com.example.darkshadow.testquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("user");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText username = (EditText)findViewById(R.id.loginUsername);
        final EditText password = (EditText)findViewById(R.id.loginPassword);
        Button login = (Button)findViewById(R.id.loginLoginButton);
        final String TAG = "TAG";

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String u=String.valueOf(username.getText().toString());
                final String p=String.valueOf(password.getText().toString());


                Log.d(TAG,u);
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                            User user1=dataSnapshot1.getValue(User.class);
                            String uf = user1.getUsername().toString();
                            String pf = user1.getPassword().toString();
                            Log.d(TAG, "onDataChange: xxu "+u);
                            Log.d(TAG, "onDataChange: xxp "+p);
                            Log.d(TAG, "onDataChange: xxuf "+user1.getUsername());
                            Log.d(TAG, "onDataChange: xxpf "+user1.getPassword());
                            if (uf.equals(u)){
                                Log.d(TAG, "onDataChange: xxuf "+user1.getUsername());
                                Log.d(TAG, "onDataChange: xxpf "+user1.getPassword());
                                final Intent intent = new Intent(Login.this, Signup.class);
                                startActivity(intent);
                            }

                            //if(user1.getPassword().equals())h
//                            for(DataSnapshot dataSnapshot2:dataSnapshot1.getChildren()){
//                                Log.d(TAG, "onDataChange: xx" + dataSnapshot2);
//                                if(dataSnapshot2.getKey().equals("password")){
//                                    if(p.equals(dataSnapshot2.getValue(String.class))){
//                                        Log.d(TAG, "onDataChange: xx1"+ "ok");
//                                    }
//                                    else{
//                                        Log.d(TAG, "onDataChange: xx1"+ "not ok");
//                                    }
//                                }
//
//
//                                else if(dataSnapshot2.getKey().equals("username")){
//                                    if(u.equals(dataSnapshot2.getValue(String.class))){
//                                        Log.d(TAG, "onDataChange: xx2"+ "ok");
//                                    }
//                                    else{
//                                        Log.d(TAG, "onDataChange: xx2"+ "not ok");
//                                    }
//                                }
//                            }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}
