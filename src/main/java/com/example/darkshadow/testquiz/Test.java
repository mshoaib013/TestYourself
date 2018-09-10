package com.example.darkshadow.testquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        final DatabaseReference mDatabase;
        final String TAG = "TAG";
        mDatabase = FirebaseDatabase.getInstance().getReference().child("c").child("s");


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Question question = dataSnapshot1.getValue(Question.class);
                    String questionn = question.getQ().toString();
                    String questionHistory = question.getQh().toString();
                    String choice1 = question.getA().toString();
                    String choice2 = question.getB().toString();
                    String choice3 = question.getC().toString();
                    String choice4 = question.getD().toString();
                    String answer = question.getAn().toString();
                    Log.d(TAG, "question xx"+questionn);
                    Log.d(TAG, "question History: xx "+questionHistory);
                    Log.d(TAG, "Choice1: xx "+choice1);
                    Log.d(TAG, "Choice2: xx "+choice2);
                    Log.d(TAG, "Choice3: xx "+choice3);
                    Log.d(TAG, "Choice4: xx "+choice4);
                    Log.d(TAG, "answer: xx "+answer);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
