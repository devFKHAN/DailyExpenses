package com.example.dailyexpenses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.text.BreakIterator;

public class loginscreen extends AppCompatActivity {
    public static Button next1;
    public static EditText et1, et2;
    public static CheckBox ck1, ck2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginscreen);
        next1 =(Button)findViewById(R.id.login1);
        et1 = findViewById(R.id.name);
        et2 = findViewById(R.id.username);
        ck1 =  findViewById(R.id.ck1);
        ck2 =  findViewById(R.id.ck2);

        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"I am here",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), reminder.class);
                startActivity(intent);
                finish();

            }
        });



    }


}