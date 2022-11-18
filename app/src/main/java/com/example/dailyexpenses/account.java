package com.example.dailyexpenses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dailyexpenses.context.login_context;
import com.example.dailyexpenses.data.InfoReaderDbHelper;

public class account extends Fragment {
    Context mcontext;
    TextView name,username,budget;
    EditText account_edit;
    Spinner spinner_editField;
    Button account_change;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mcontext = container.getContext();
        return inflater.inflate(R.layout.fragment_account, container,false );
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name=view.findViewById(R.id.account_name);
        username=view.findViewById(R.id.account_username);
        spinner_editField=view.findViewById(R.id.account_spinner);
        budget=view.findViewById(R.id.account_budget);
        account_edit=view.findViewById(R.id.account_edit);
        account_change=view.findViewById(R.id.account_change);


        // details field
        try {
            SQLiteOpenHelper infoReaderDbHelper = new InfoReaderDbHelper(mcontext);
            SQLiteDatabase db = infoReaderDbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("Select * from login_detail ", null);
            if (cursor.moveToFirst()) {
                name.setText(cursor.getString(1));
                username.setText(cursor.getString(2));
                budget.setText(String.valueOf(cursor.getInt(4)).concat(" ₹"));

                db.close();
                cursor.close();
            }
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(getActivity(), "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }


        //spinner
        ArrayAdapter adapter = ArrayAdapter.createFromResource(mcontext, R.array.changing_field,R.layout.style);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner_editField.setAdapter(adapter);

        // edit field
        spinner_editField.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spinner_editField.getSelectedItem().toString().matches("Budget")){
                    account_edit.setInputType(InputType.TYPE_CLASS_NUMBER);
                }
                else {
                    account_edit.setInputType(InputType.TYPE_CLASS_TEXT);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        account_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(account_edit.getText().toString().matches("")){
                    Toast.makeText(mcontext, "FILL DETAILS FIRST", Toast.LENGTH_SHORT).show();
                }
                else{
                SQLiteOpenHelper infoReaderDbHelper = new InfoReaderDbHelper(mcontext);
                SQLiteDatabase db = infoReaderDbHelper.getWritableDatabase();
                ContentValues contentValues=new ContentValues();
                contentValues.put((String) spinner_editField.getSelectedItem(),account_edit.getText().toString());
                db.update("login_detail",contentValues,login_context.login_detail._ID+"=?",new String[]{String.valueOf(1)});


                try {

                    db = infoReaderDbHelper.getReadableDatabase();
                    Cursor cursor = db.rawQuery("Select * from login_detail ", null);
                    if (cursor.moveToFirst()) {
                        name.setText(cursor.getString(1));
                        username.setText(cursor.getString(2));
                        budget.setText(String.valueOf(cursor.getInt(4)).concat(" ₹"));

                        db.close();
                        cursor.close();
                    }
                } catch (SQLiteException e) {
                    Toast toast = Toast.makeText(getActivity(), "Database unavailable", Toast.LENGTH_SHORT);
                    toast.show();
                }
                account_edit.setText("");

            }
            }
        });




    }
}
