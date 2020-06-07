package com.example.roadways;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class offense extends AppCompatActivity{
    private ImageView mDialog;
    LocationListener locationListener;LocationManager locationManager;
    ArrayList<String> vehicle=new ArrayList<>();ArrayList<String> listviolate=new ArrayList<>();ArrayList<Integer> cost=new ArrayList<>();
    public static String finmess;String curoff="";int poos,coist;public static String successpay="";
    public static String sendnoc="\t\tThe High Court Of India\n\nRespected Sir/Madam,\nSubject:NC regarding challan number-";
    ArrayList<String> userall=new ArrayList<>();public static ArrayList<String> useralls=new ArrayList<>();int posit=0;
    ArrayList<String> yourfoul=new ArrayList<>();ArrayList<String> yourcost=new ArrayList<>();
    public static String adding="";public static boolean flag=false;
    public static String address="Jagmohan Nagar\nBhuvaneshwar\nOdisha-751030";
    Button button12;
    public static ArrayList<String> vehicles=new ArrayList<>();
    SharedPreferences sharedPreferences;
    public static String mailmessage="Mumbai Traffic Police\n\n---------------------------------\nChallan No:";int pos=0;
    long sum=0;
    EditText editText,editText2;

    public void enteer(/*View view*/){
        Toast.makeText(this,"Successfully entered in our database",Toast.LENGTH_SHORT).show();
        Toast.makeText(this,address.toString(),Toast.LENGTH_SHORT).show();
        String crimes="";boolean flag=false;
        mailmessage+="\nVehicle Number:"+editText.getText().toString();
        mailmessage+="\nofficer name:"+signupact.officerid.get(pos);
        mailmessage+="\nOn account of:"+editText2.getText().toString()+"\n";
        //finmess=mailmessage;
        for(int i=0;i<yourfoul.size();i++){
            crimes+=yourfoul.get(i)+"--->"+yourcost.get(i)+"\n";
        }

        mailmessage+="Latitude:"+String.valueOf(MainActivity.fixlat);
        mailmessage+="\nLongitude"+String.valueOf(MainActivity.fixlon);
        mailmessage+="\nAddress:\n"+address+"\n";
        //Toast.makeText(this,MainActivity.addresss.toString(),Toast.LENGTH_SHORT).show();

        mailmessage+="OFFENSE(s) WITH COST\n"+crimes+"Total Cost:"+String.valueOf(sum);
        mailmessage+="\nSend using paytm challan pay: https://paytm.com/challan-bill-payment/pune";
        mailmessage+="\nPay using Government Receipt Accounting System: https://gras.mahakosh.gov.in/echallan/";
        mailmessage+="\n\n\nNOTE:If the fine is not paid within 10 days from the date of email reception, strict action can be taken against the defaulter.";
        finmess+="Total Cost:"+String.valueOf(sum);
        finmess+="\nSedn using paytm challan pay: https://paytm.com/challan-bill-payment/pune";
        finmess+="\nPay using Government Receipt Accounting System: https://gras.mahakosh.gov.in/echallan/";
        for(int i=0;i<vehicles.size();i++){
            if(vehicles.get(i).equals(editText.getText().toString())){
                flag=true;posit=i;break;
            }
        }
        if(flag){
            crimes+="\n\n\n"+DateFormat.getDateTimeInstance().format(new Date())+"\n"+useralls.get(posit);
            useralls.add(posit,crimes);
            Toast.makeText(this,"already exists in database.click to Check history or continue to send",Toast.LENGTH_LONG).show();
            button12.setVisibility(View.VISIBLE);
        }
        else{
            useralls.add(crimes);
            vehicles.add(editText.getText().toString());
        }

        try {
            sharedPreferences.edit().putString("vehicle",ObjectSerializer.serialize(vehicles)).apply();
            sharedPreferences.edit().putString("userall",ObjectSerializer.serialize(useralls)).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Toast.makeText(this,mailmessage,Toast.LENGTH_SHORT).show();
        successpay=mailmessage;
        successpay+="\nYou have successfully paid the challan";
    }
    public void scanqr(View view){


        Dialog settingsDialog = new Dialog(this);
        settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        settingsDialog.setContentView(getLayoutInflater().inflate(R.layout.image_layout,null));
        settingsDialog.show();



    }
    public void paynow(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm dialog demo !");
        builder.setMessage("You will now be directed to payment window for a time limit of 10 minutes.Continue?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "WWelcome to the Payment window", Toast.LENGTH_SHORT).show();
                if(!flag){enteer();flag=true;}
                Intent intent=new Intent(getApplicationContext(),googlepay.class);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You've changed your mind", Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();
        /*if(!flag){enteer();flag=true;}
        Intent intent=new Intent(getApplicationContext(),googlepay.class);
        startActivity(intent);*/
        /*Intent intents = new Intent(Intent.ACTION_SEND);
        intents.putExtra(Intent.EXTRA_EMAIL, "yrtyagi@gmail.com");
        intents.putExtra(Intent.EXTRA_SUBJECT, "confirmation of challan payment");
        intents.putExtra(Intent.EXTRA_TEXT,successpay);
        intents.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));*/
    }
    public void getdata(View view){
        Intent intent=new Intent(getApplicationContext(),getdata.class);
        intent.putExtra("messi",posit);
        startActivity(intent);
    }
    public void gotomail(View view){
        if(!flag){enteer();flag=true;}
        /*mailmessage+="\nSedn using paytm challan pay: https://paytm.com/challan-bill-payment/pune";
        mailmessage+="\nPay using Government Receipt Accounting System: https://gras.mahakosh.gov.in/echallan/";
        mailmessage+="\n\n\nNOTE:If the fine is not paid within 10 days from the date of email reception, strict action can be taken against the defaulter.";
        finmess+="Total Cost:"+String.valueOf(sum);
        finmess+="\nSedn using paytm challan pay: https://paytm.com/challan-bill-payment/pune";
        finmess+="\nPay using Government Receipt Accounting System: https://gras.mahakosh.gov.in/echallan/";*/
        //finmess+="\n\n\nNOTE:If the fine is not paid within 10 days from the date of email reception, strict action can be taken against the defaulter.";
        Intent intents=new Intent(this,sendmail.class);
        intents.putExtra("message",mailmessage);
        startActivity(intents);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offense);
        button12=(Button) findViewById(R.id.button12);
        final Button but15=(Button) findViewById(R.id.button15);
        Geocoder geocoder=new Geocoder(getApplicationContext(), Locale.getDefault());
        //Toast.makeText(this,adding,Toast.LENGTH_SHORT).show();
        try {
            List<Address> listaddress=geocoder.getFromLocation(MainActivity.fixlat,MainActivity.fixlon,1);
            if(listaddress.get(0).getThoroughfare()!=null){
                address+=listaddress.get(0).getThoroughfare().toString()+"\n";
            }
            if(listaddress.get(0).getLocality()!=null){
                address+=listaddress.get(0).getLocality().toString()+"\n";
            }
            if(listaddress.get(0).getPostalCode()!=null){
                address+=listaddress.get(0).getPostalCode().toString()+" ";
            }
            if(listaddress.get(0).getAdminArea()!=null){
                address+=listaddress.get(0).getAdminArea().toString()+" ";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("iknowadd",address);
        final ListView listView=(ListView) findViewById(R.id.listview);
        final TextView textView=(TextView) findViewById(R.id.textView5);
        Random random=new Random();yourfoul.clear();yourcost.clear();
        editText=(EditText) findViewById(R.id.editText6);
        editText2=(EditText) findViewById(R.id.editText5);
        long challannumber=random.nextInt(100001);
        sendnoc+=String.valueOf(challannumber);
        sendnoc+="\tThis is an application for filing a case against the above mentioned challan number.The following person was found guilty ";
        mailmessage+=String.valueOf(challannumber);
        mailmessage+="\nDate and time:   "+ DateFormat.getDateTimeInstance().format(new Date())+"\n---------------------------------";
        Intent intent=getIntent();
        pos=intent.getIntExtra("message",0);
        listviolate.add("Driving by a minor");listviolate.add("no license");listviolate.add("driving 2 wheeler without helmet");
        listviolate.add("driving without seat belts fastened");listviolate.add("rough or reckless driving");
        listviolate.add("Hazardous or hasty driving");listviolate.add("not driving in proper lane");
        listviolate.add("driving in centre");listviolate.add("driving against one way");listviolate.add("reversing wihtout caution");
        listviolate.add("use of offensive number plate");listviolate.add("using high beam when not needed");
        listviolate.add("not wearing seat belt");listviolate.add("overloading of passengers");listviolate.add("overloading of goods");
        listviolate.add("driving under influence of alcohol");
        cost.add(500);cost.add(1000);cost.add(100);cost.add(100);cost.add(1000);
        cost.add(1000);cost.add(200);cost.add(100);cost.add(100);cost.add(100);cost.add(100);cost.add(100);cost.add(1000);
        cost.add(1000);cost.add(2000);cost.add(4000);


        sharedPreferences=this.getSharedPreferences("com.example.sharedpreferences", Context.MODE_PRIVATE);
        /*vehicle.clear();vehicles.clear();userall.clear();useralls.clear();
        vehicle.add("12345");
        userall.add("no helmet");
        //.add("ramesh@123");password.add("abcd");
        try {
            sharedPreferences.edit().putString("vehicle",ObjectSerializer.serialize(vehicle)).apply();
            sharedPreferences.edit().putString("userall",ObjectSerializer.serialize(userall)).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        try {
            vehicles=(ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("vehicle",ObjectSerializer.serialize(new ArrayList<String>())));
            useralls=(ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("userall",ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("vehicleact",vehicles.toString());
        Log.i("vehicleacts",useralls.toString());
        final ArrayAdapter<String> ada=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,listviolate);
        listView.setAdapter(ada);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                /*if(listviolate.get(position).equals("driving under influence of alcohol")){
                    Intent intenti=new Intent(getApplicationContext(),nonc.class);
                    startActivity(intenti);
                }*/
                curoff=listviolate.get(position);poos=position;
                coist=cost.get(position);
                yourfoul.add(listviolate.get(position));yourcost.add(String.valueOf(cost.get(position)));
                sum+=cost.get(position);
                textView.setText("Total:"+String.valueOf(sum));
               //ada.remove(listviolate.get(position));
                listviolate.remove(position);
                cost.remove(position);
                listView.setAdapter(ada);
                //Toast.makeText(this,"added",Toast.LENGTH_SHORT).show();
            }
        });
        but15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listviolate.add(poos,curoff);
                cost.add(poos,coist);
                sum-=coist;
                textView.setText("Total:"+String.valueOf(sum));
                listView.setAdapter(ada);
            }

        });
    }
}
