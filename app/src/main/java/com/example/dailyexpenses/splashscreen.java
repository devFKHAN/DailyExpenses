package com.example.dailyexpenses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import com.example.dailyexpenses.Details.logindetails;
import com.example.dailyexpenses.context.dailyUpdate_context;
import com.example.dailyexpenses.context.login_context;
import com.example.dailyexpenses.data.InfoReaderDbHelper;

import java.util.List;

public class splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        Intent in=new Intent(splashscreen.this,loginscreen.class);
        Intent in1=new Intent(splashscreen.this,MainActivity.class);
        SQLiteOpenHelper db=new InfoReaderDbHelper(this);
        Cursor resultSet =db.getReadableDatabase().rawQuery("Select * from login_detail ",null);
        Thread th = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                        if (resultSet.getCount()>0) {
                            startActivity(in1);
                        } else {
                            startActivity(in);
                        }
                    }
                    finish();
                }


        };
        th.start();



    }
    protected void onPause() {
        super.onPause();
        finish();
    }

}
