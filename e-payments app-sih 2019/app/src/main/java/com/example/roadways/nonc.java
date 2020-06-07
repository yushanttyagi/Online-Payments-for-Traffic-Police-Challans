package com.example.roadways;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class nonc extends AppCompatActivity {

    private TextView mEditTextTo;
    private EditText mEditTextSubject;
    private EditText mEditTextMessage;
    private EditText meditText2;
    private CheckBox mcheckBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mcheckBox=findViewById(R.id.checkBox);
        mEditTextTo = findViewById(R.id.emailID);
        mEditTextSubject = findViewById(R.id.editText);
        mEditTextMessage = findViewById(R.id.edit_text_message);
        meditText2=findViewById(R.id.editText2);
        Button buttonSend = findViewById(R.id.button_send);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });
    }

    private void sendMail() {
        String recipientList = mEditTextTo.getText().toString();
        String Fine=meditText2.getText().toString();
        String[] recipients = recipientList.split(",");
        if(mcheckBox.isChecked()==false){
            Fine="0";
        }
        String subject = mEditTextSubject.getText().toString();
        String message = "Respected Sir/Madam,\n This is an application for filing a case to be judged by the court. The case to be filled is for:-\n";
        // message=message+offence.mailmessage;
        message=message+"The fine charged was:-"+Fine;
        message=message+"\nPlease revert back to the given email with the date of hearing ,the time and the venue";
        message=message+"\n\nThank You.";
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }
}


