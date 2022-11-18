package com.example.dailyexpenses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.TextView;

import com.example.dailyexpenses.data.InfoReaderDbHelper;
import com.example.dailyexpenses.Details.logindetails;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import static com.example.dailyexpenses.loginscreen.et1;
import static com.example.dailyexpenses.loginscreen.et2;

public class MainActivity extends AppCompatActivity {
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new home()).commit();
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);


    }
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener=new BottomNavigationView.OnNavigationItemSelectedListener(){
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selected_fragment=null;
            switch (item.getItemId())
            {
                case R.id.home:
                    selected_fragment=new home();
                    break;
                case R.id.graph:
                    selected_fragment=new graph();
                    break;
                case R.id.Account:
                    selected_fragment=new account();
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selected_fragment).commit();
            return true;
        }
    };
}


