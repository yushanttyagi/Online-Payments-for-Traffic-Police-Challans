package com.example.roadways;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class getdata extends AppCompatActivity {
TextView textView,textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getdata);
        textView=(TextView) findViewById(R.id.textView6);
        textView2=(TextView) findViewById(R.id.textView9);
        Intent intent=getIntent();
        int messi=intent.getIntExtra("messi",0);
        String fines=offense.useralls.get(messi);
        String name=offense.vehicles.get(messi);
        textView2.setText(name);
        textView.setText(fines);
    }
}
