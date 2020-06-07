package com.example.roadways;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class sendsms extends AppCompatActivity {
    private EditText send;private static final int permission=1;String messii;
    Button button;EditText phone;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case permission:if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"accepted",Toast.LENGTH_SHORT).show();
            }
            else{
                button=(Button) findViewById(R.id.button11);button.setEnabled(false);
                Toast.makeText(this,"denied",Toast.LENGTH_SHORT).show();
            }break;
        }
    }
    private boolean checkpermission(){
        int result= ContextCompat.checkSelfPermission(sendsms.this, Manifest.permission.SEND_SMS);
        if(result== PackageManager.PERMISSION_GRANTED){
            return true;
        }
        else{
            return false;
        }
    }
    private void requestpermission(){
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},permission);
    }
    public void sendsms(View view){
        String pho=phone.getText().toString();
        if(checkpermission()){
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(pho,null,offense.mailmessage,null,null);
        }
        else{
            Toast.makeText(this,"not possible",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendsms);
        phone=(EditText) findViewById(R.id.editText8);
       Intent intent=getIntent();
        messii=intent.getStringExtra("smstext");
       // messii="hello yushant tyagi";
        if(Build.VERSION.SDK_INT>=23){
            if(checkpermission()){
                Toast.makeText(this,"yes granted",Toast.LENGTH_SHORT).show();
            }
            else{
                requestpermission();
            }
        }
    }
}
