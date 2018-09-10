package com.example.darkshadow.testquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TopicChoose extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_choose);
        Button ssc = (Button)findViewById(R.id.topicchooseSSC);
        Button hsc = (Button)findViewById(R.id.topicchooseHSC);
        ssc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(TopicChoose.this, Home.class);
                startActivity(intent);
            }
        });
        hsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(TopicChoose.this, Home.class);
                startActivity(intent);
            }
        });
    }
}
