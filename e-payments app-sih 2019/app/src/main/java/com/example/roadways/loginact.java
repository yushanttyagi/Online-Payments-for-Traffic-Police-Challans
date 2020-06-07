package com.example.roadways;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class loginact extends AppCompatActivity {
    SharedPreferences sharedPreference;
    EditText e3,e4;public static String custar="";
    public static ArrayList<Integer> noofch=new ArrayList<>();
    public void gohome(View view){
        finish();
    }
    public void clicktologin(View view){
        String id=e3.getText().toString(),pass=e4.getText().toString();boolean flag=false;int pos=0;
        Log.i("offid",id);
        Log.i("passisis",pass);
        Log.i("loginofficer",signupact.officerid.toString());
        for(int i=0;i<signupact.officerid.size();i++){
            if(signupact.officerid.get(i).equals(id)){
                flag=true;pos=i;break;
            }
        }
        custar+="\nYou have fined "+noofch.get(pos).toString()+" fines";
        int hhgg=noofch.get(pos);
        //noofch.add(1,5);
        noofch.add(pos,hhgg++);
        try{
            sharedPreference.edit().putString("noofch",ObjectSerializer.serialize(noofch)).apply();
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.i("noofaff",noofch.toString());
        if(flag){
            if(signupact.password.get(pos).equals(pass)){
                Intent intent=new Intent(this,officerlog.class);
                intent.putExtra("message",pos);
                startActivity(intent);
                //Toast.makeText(this,"succes",Toast.LENGTH_SHORT).show();
            }
            else{
                view = this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                Toast.makeText(this,"incorrect password.try again",Toast.LENGTH_SHORT).show();
            }
        }
        else{
            view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            Toast.makeText(this,"not found in our database.please sign up",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginact);
        Intent intent=getIntent();
        /*for(int i=0;i<1000;i++){
            noofch.add(i,0);
        }
        noofch.add(1,8);*/
        int max=0,pol = 0;
        for(int i=0;i<noofch.size();i++){
            if(max<noofch.get(i)){
                max=noofch.get(i);pol=i;
            }
        }
        //custar+=signupact.password.get(pol);
        custar+="\nThis month's star performer is "+signupact.officerid.get(pol);
        sharedPreference=this.getSharedPreferences("com.example.sharedpreferences", Context.MODE_PRIVATE);
        signupact.sharedPreferences=this.getSharedPreferences("com.example.sharedpreferences", Context.MODE_PRIVATE);
        /*try {
            sharedPreferences.edit().putString("noofch",ObjectSerializer.serialize(noofch)).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("noofch",noofch.toString());*/
        try {
            noofch= (ArrayList<Integer>) ObjectSerializer.deserialize(sharedPreference.getString("noofch",ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("noofch2",noofch.toString());

        try {
            signupact.officerid=(ArrayList<String>) ObjectSerializer.deserialize(signupact.sharedPreferences.getString("office",ObjectSerializer.serialize(new ArrayList<String>())));
            signupact.password=(ArrayList<String>) ObjectSerializer.deserialize(signupact.sharedPreferences.getString("pass",ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String message=intent.getStringExtra("message");
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
        e3=(EditText) findViewById(R.id.editText3);
        e4=(EditText) findViewById(R.id.editText4);
        Log.i("loginofficer",signupact.officerid.toString());
    }
}
