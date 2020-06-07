package com.example.roadways;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    LocationManager locationManager;LocationListener locationListener;
    public static double fixlat,fixlon;public static String addresss="";
   // EditText editText3,editText4;
    public void signup(View view){
        Intent intent=new Intent((getApplicationContext()),signupact.class);
        intent.putExtra("message","welcome to the RTO login page");
        startActivity(intent);
    }
    /*public void ghgh(View view){
        EditText eu=(EditText) findViewById(R.id.editText7);EditText ue=(EditText) findViewById(R.id.editText8);
        Toast.makeText(this, eu.getText().toString(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, ue.getText().toString(), Toast.LENGTH_SHORT).show();
    }*/
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                }
            }
        }
    }
    public void login(View view){
        Intent intent=new Intent((getApplicationContext()),loginact.class);
        intent.putExtra("message","welcome to the login page");
        startActivity(intent);
    }
    public void loggin(View view){
        Intent intent=new Intent((getApplicationContext()),loginact.class);
        intent.putExtra("message","welcome to the login page");
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView=(TextView) findViewById(R.id.textView8);
       // textView.setMovementMethod(new ScrollingMovementMethod());
        //textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView.setSelected(true);
        locationManager=(LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        locationListener=new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                fixlat=location.getLatitude();
                fixlon=location.getLongitude();
                //Toast.makeText(getApplicationContext(),String.valueOf(fixlat),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        if (Build.VERSION.SDK_INT < 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
        else{
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
            else{
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
                Location lastknown=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            }
        }


    }


}
