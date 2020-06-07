package com.example.roadways;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class officerlog extends AppCompatActivity {
    int pos=0;TextView textView;
    public void gotooffense(View view){
        Intent intent=new Intent(this,offense.class);
        intent.putExtra("message",pos);
        startActivity(intent);
    }
    public void gotoprofile(View view){
        textView.setText(loginact.custar);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officerlog);
        Intent intent=getIntent();
        textView=(TextView) findViewById(R.id.textView7);
        pos=intent.getIntExtra("message",0);
        Toast.makeText(this,"welcome "+signupact.officerid.get(pos),Toast.LENGTH_SHORT).show();
    }
}
