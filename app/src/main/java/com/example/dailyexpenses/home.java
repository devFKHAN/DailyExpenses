package com.example.dailyexpenses;

import android.app.DatePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dailyexpenses.Details.dailyDetails;
import com.example.dailyexpenses.Details.logindetails;
import com.example.dailyexpenses.context.login_context;
import com.example.dailyexpenses.data.InfoReaderDbHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class home extends Fragment {
    Context context;
    TextView tv,date,log,progress_bar_text;
    ImageView img;
    EditText price,description;
    Spinner spinner;
    DatePickerDialog.OnDateSetListener mDateSetListener;
    String print_date;
    ListView listView;
    Button note_down;
    int budget,spent,percent;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = container.getContext();
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // initialise your views\
        note_down=view.findViewById(R.id.note_down);
        description=(EditText) view.findViewById(R.id.description);
        price=(EditText) view.findViewById(R.id.price);
        tv = (TextView) view.findViewById(R.id.user_tx);
        img = (ImageView) view.findViewById(R.id.img);
        listView=view.findViewById(R.id.listing);
        progressBar=view.findViewById(R.id.progress_bar);
        progress_bar_text=view.findViewById(R.id.progress_bar_text);
        tv.setText("hello");
        try {
            SQLiteOpenHelper infoReaderDbHelper = new InfoReaderDbHelper(context);
            SQLiteDatabase db = infoReaderDbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("Select * from login_detail ", null);
            if (cursor.moveToFirst()) {
                tv.setText(cursor.getString(2));
                budget=cursor.getInt(4);
                if (cursor.getString(3).equals("MALE")) {
                    img.setImageResource(R.drawable.male);
                } else
                    img.setImageResource(R.drawable.female);
                db.close();
                cursor.close();
            }

        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(getActivity(), "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
        spinner = (Spinner) view.findViewById(R.id.categories);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(context, R.array.categories,R.layout.style);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(adapter);

        //date of note down
        date = (TextView)view.findViewById(R.id.date_txt);
        Date d=new Date();
        date.setText(DateFormat.format("dd-MM-yyyy", d.getTime()));
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        context,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                String print_date1 = day+ "-" + month + "-" + year;

                if(month<10){
                    print_date1 = day+ "-0" + month + "-" + year;
                }
                date.setText(print_date1);
            }
        };


        note_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoReaderDbHelper db = new InfoReaderDbHelper(context);
                dailyDetails details = new dailyDetails();
                details.setCategories((String) spinner.getSelectedItem());
                details.setPrice(Integer.parseInt(String.valueOf(price.getText())));
                details.setDate((String) date.getText());
                if (description.getText().toString().matches(""))
                    description.setText("NOTHING");
                details.setDescription(String.valueOf(description.getText()));
                db.insertDailyUpdates(details);
                db.close();

                //making text to its initial state

                description.setText("");
                price.setText("");

                //updating list view

                InfoReaderDbHelper db1 = new InfoReaderDbHelper(context);
                List<dailyDetails> list=db1.getAllDailyUpdates();
                ViewGroup.LayoutParams layoutParams=listView.getLayoutParams();
                layoutParams.height=list.size()*130;
                listView.setLayoutParams(layoutParams);
                customAdapter adapter1=new customAdapter(context,R.layout.row,list);
                listView.setAdapter(adapter1) ;

                //progress bar

                spent=db1.sum;
                if(budget-spent<0){
                    percent=((spent-budget)*100)/budget;
                    progressBar.setProgress(percent);
                    progress_bar_text.setText("-"+String.valueOf(percent)+"%");
                }
                else{
                    percent=((budget-spent)*100)/budget;
                    progressBar.setProgress(percent);
                    progress_bar_text.setText(String.valueOf(percent)+"%");

                }
                db1.close();

            }
        });

        // table view

        InfoReaderDbHelper db2 = new InfoReaderDbHelper(context);
        List<dailyDetails> list=db2.getAllDailyUpdates();
        ViewGroup.LayoutParams layoutParams=listView.getLayoutParams();
        layoutParams.height=list.size()*135;
        listView.setLayoutParams(layoutParams);
        Log.d("countfaizan",String.valueOf(list.size()));
        customAdapter adapter1=new customAdapter(context,R.layout.row,list);
        listView.setAdapter(adapter1) ;

        //progress bar
        spent=db2.sum;
        if(budget-spent<0){
            percent=((spent-budget)*100)/budget;
            progressBar.setProgress(percent);
            progress_bar_text.setText("-"+String.valueOf(percent)+"%");
        }
        else{
            percent=((budget-spent)*100)/budget;
            progressBar.setProgress(percent);
            progress_bar_text.setText(String.valueOf(percent)+"%");

        }


    }




}