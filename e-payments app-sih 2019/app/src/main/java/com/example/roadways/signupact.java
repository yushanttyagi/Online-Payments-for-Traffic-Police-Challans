package com.example.roadways;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class signupact extends AppCompatActivity {
    EditText e1,e2;ArrayAdapter ada1,ada2;ListView listview,listview2;EditText rtopass;
    public static SharedPreferences sharedPreferences;
    Button but3,but14;
    public static ArrayList<String> officerid=new ArrayList<>();
    public static ArrayList<String> password=new ArrayList<>();
    public void gohome(View view){
        finish();
    }
    public void signup(View view){
        officerid.add(e1.getText().toString());password.add(e2.getText().toString());
        try{
            sharedPreferences.edit().putString("office",ObjectSerializer.serialize(officerid)).apply();
            sharedPreferences.edit().putString("pass",ObjectSerializer.serialize(password)).apply();
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.i("newofficer",officerid.toString());
        Log.i("newpassword",password.toString());
    }
   /* public static void hidekey(Activity activity){
        InputMethodManager inputMethodManager=(InputMethodManager)activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view=activity.getCurrentFocus();
        if(view==null){
            view=new View(activity);
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }*/
    public void checkpass(View view){
        //hidekey();
        view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        if(rtopass.getText().toString().equals("rto123")){
            Toast.makeText(this,"Hello Officer",Toast.LENGTH_SHORT).show();
            e1.setVisibility(View.VISIBLE);
            e2.setVisibility(View.VISIBLE);
            but3.setVisibility(View.VISIBLE);
            rtopass.setVisibility(View.INVISIBLE);
            but14.setVisibility(View.INVISIBLE);
        }
        else{
            Toast.makeText(this,"WRONG PASSWORD.TRY AGAIN",Toast.LENGTH_SHORT).show();
            rtopass.setText("");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupact);
        Intent intent=getIntent();
        but3=(Button) findViewById(R.id.button3);
        but14=(Button) findViewById(R.id.button14);
        String message=intent.getStringExtra("message");
      /*  listview=(ListView) findViewById(R.id.listview);
        listview2=(ListView) findViewById(R.id.listview2);*/
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
        e1=(EditText) findViewById(R.id.editText);
        e2=(EditText) findViewById(R.id.editText7);
        rtopass=(EditText) findViewById(R.id.editText44);
        sharedPreferences=this.getSharedPreferences("com.example.sharedpreferences", Context.MODE_PRIVATE);
        /*officerid.add("ramesh@123");password.add("abcd");
        try {
            sharedPreferences.edit().putString("office",ObjectSerializer.serialize(officerid)).apply();
            sharedPreferences.edit().putString("pass",ObjectSerializer.serialize(password)).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        try {
            officerid=(ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("office",ObjectSerializer.serialize(new ArrayList<String>())));
            password=(ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("pass",ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("officer",officerid.toString());
        Log.i("password",password.toString());
        /*ada1=new ArrayAdapter(this,android.R.layout.simple_list_item_single_choice,officerid);
        listview.setAdapter(ada1);
        ada2=new ArrayAdapter(this,android.R.layout.simple_list_item_single_choice,password);
        listview2.setAdapter(ada2);*/

    }
}
