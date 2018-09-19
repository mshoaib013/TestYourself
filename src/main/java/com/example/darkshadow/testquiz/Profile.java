package com.example.darkshadow.testquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        EditText username = (EditText) findViewById(R.id.profileUsername);
        EditText bio = (EditText) findViewById(R.id.profileBio);
        final EditText emailPhone = (EditText) findViewById(R.id.profilePhoneOrEmail);
        TextView Score = (TextView) findViewById(R.id.profileScore);
        TextView Rank = (TextView) findViewById(R.id.profileRank);
        final DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("U").child("1");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String strUsername = dataSnapshot.child("Email").getValue().toString();
                String strBio = dataSnapshot.child("P").getValue().toString();
                String strEmailPhone = dataSnapshot.child("Email").getValue().toString();
                String strScore = dataSnapshot.child("Email").getValue().toString();
                String strRank = dataSnapshot.child("Email").getValue().toString();
                Log.d("xx", "onDataChange: "+dataSnapshot.child("Email").getValue());
                emailPhone.setText(strEmailPhone);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
