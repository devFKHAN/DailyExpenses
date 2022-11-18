package com.example.dailyexpenses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.dailyexpenses.Details.logindetails;
import com.example.dailyexpenses.data.InfoReaderDbHelper;

import java.util.List;

import static com.example.dailyexpenses.loginscreen.ck1;
import static com.example.dailyexpenses.loginscreen.et1;
import static com.example.dailyexpenses.loginscreen.et2;

public class reminder extends AppCompatActivity {
    public static EditText bug;
    public static TimePicker remender;
    public static String time;
    Button done;
    public static final int flag=0;
    public static String gen="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        Toast.makeText(getApplicationContext(),"I am here",Toast.LENGTH_LONG).show();
        remender=findViewById(R.id.rem);
        bug=findViewById(R.id.budget);
        done=findViewById(R.id.done_btn);
        remender.setIs24HourView(false);
        remender.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
               time=Integer.toString(hourOfDay)+":"+Integer.toString(minute);
            }
        });
        if (ck1.isChecked()) {
            gen="MALE";
        } else {
            gen="FEMALE";
        }

    }

    public void mainview(View view) {
        InfoReaderDbHelper db=new InfoReaderDbHelper(this);
        logindetails aoogi =new logindetails();
        aoogi.setCOLUMN_NAME(et1.getText().toString());
        aoogi.setCOLUMN_USERNAME(et2.getText().toString());
        aoogi.setCOLUMN_GENDER(reminder.gen);
        aoogi.setCOLUMN_BUDGET(Integer.parseInt(reminder.bug.getText().toString()));
        aoogi.setCOLUMN_TIME(reminder.time);
        db.insertDetail(aoogi);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}