package com.example.dailyexpenses.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.dailyexpenses.Details.dailyDetails;
import com.example.dailyexpenses.context.dailyUpdate_context;
import com.example.dailyexpenses.Details.logindetails;

import java.util.ArrayList;
import java.util.List;

import static com.example.dailyexpenses.context.login_context.*;

public class InfoReaderDbHelper extends SQLiteOpenHelper {
    public int flag=0;
    public int sum;
    public static final String DB_NAME = "expense_db"; // the name of our database
    private static final int DB_VERSION = 1; // the version of the database
    private static final String SQL_CREATE_ENTRIES1="CREATE TABLE "+ login_detail.TABLE_NAME+"("
            + login_detail._ID+ " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            login_detail.COLUMN_NAME+" TEXT, "+
            login_detail.COLUMN_USERNAME+" TEXT, "+
            login_detail.COLUMN_GENDER+" TEXT, "+
      //      login_detail.COLUMN_IMAGE_RESOURCE_ID+"INTEGER, "+
            login_detail.COLUMN_BUDGET+" INTEGER, "+
            login_detail.COLUMN_TIME + " TEXT);";
    public static final String SQL_CREATE_ENTRIES2="CREATE TABLE "+ dailyUpdate_context.dailyUpdate.TABLE_NAME+"("
            +dailyUpdate_context.dailyUpdate._ID1+ " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            dailyUpdate_context.dailyUpdate.COLUMN_DATE+" TEXT, "+
            dailyUpdate_context.dailyUpdate.COLUMN_CATEGORIES+" TEXT, "+
            dailyUpdate_context.dailyUpdate.COLUMN_DESCRIPTION+" TEXT, "+
            dailyUpdate_context.dailyUpdate.COLUMN_PRICE+" TEXT, "+
            dailyUpdate_context.dailyUpdate.COLUMN_DAY+" NUMERIC, "+
            dailyUpdate_context.dailyUpdate.COLUMN_MONTH+" NUMARIC, "+
            dailyUpdate_context.dailyUpdate.COLUMN_YEAR+" NUMERIC);";
    public InfoReaderDbHelper(Context context) { super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES1);
        db.execSQL(SQL_CREATE_ENTRIES2);
        Log.d("faizuu","data base created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertDetail(logindetails d) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(login_detail.COLUMN_NAME,d.getCOLUMN_NAME());
        values.put(login_detail.COLUMN_USERNAME,d.getCOLUMN_USERNAME());
        values.put(login_detail.COLUMN_GENDER, d.getCOLUMN_GENDER());
        values.put(login_detail.COLUMN_BUDGET,d.getCOLUMN_BUDGET());
        values.put(login_detail.COLUMN_TIME,d.getCOLUMN_TIME());
        db.insert(login_detail.TABLE_NAME, null, values);
        db.close();
    }
    public List<logindetails> getAllDetails(){
        List<logindetails> d=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        String select="SELECT * FROM "+ login_detail.TABLE_NAME;
        Cursor cursor=db.rawQuery(select,null);
        int i=0;
        if(cursor.moveToFirst()){
            do {
                logindetails dt=new logindetails();
                dt.setId(Integer.parseInt(cursor.getString(0)));
                dt.setCOLUMN_NAME(cursor.getString(1));
                dt.setCOLUMN_USERNAME(cursor.getString(2));
                dt.setCOLUMN_GENDER(cursor.getString(3));
                dt.setCOLUMN_BUDGET(cursor.getInt(4));
                dt.setCOLUMN_TIME(cursor.getString(5));
                d.add(dt);
            }while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return d;
    }
    public void insertDailyUpdates(dailyDetails d) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(dailyUpdate_context.dailyUpdate.COLUMN_DATE,d.getDate());
        values.put(dailyUpdate_context.dailyUpdate.COLUMN_CATEGORIES,d.getCategories());
        values.put(dailyUpdate_context.dailyUpdate.COLUMN_DESCRIPTION,d.getDescription());
        values.put(dailyUpdate_context.dailyUpdate.COLUMN_PRICE,d.getPrice());
        values.put(dailyUpdate_context.dailyUpdate.COLUMN_DAY,Integer.parseInt(d.getDate().substring(0,2)));
        values.put(dailyUpdate_context.dailyUpdate.COLUMN_MONTH,Integer.parseInt(d.getDate().substring(3,5)));
        values.put(dailyUpdate_context.dailyUpdate.COLUMN_YEAR,Integer.parseInt(d.getDate().substring(6,10)));

        db.insert(dailyUpdate_context.dailyUpdate.TABLE_NAME, null, values);
        db.close();
    }
    public List<dailyDetails> getAllDailyUpdates(){
        sum=0;
        List<dailyDetails> list=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        String select="SELECT * FROM "+dailyUpdate_context.dailyUpdate.TABLE_NAME;
        Cursor cursor=db.rawQuery(select,null);
        if(cursor.moveToLast()){
            do{
                dailyDetails details=new dailyDetails();
                details.setCategories(cursor.getString(2));
                details.setPrice(Integer.parseInt(cursor.getString(4)));
                sum += cursor.getInt(4);
                details.setDate((cursor.getString(1)));
                details.setDescription(cursor.getString(3));
                details.setDay(cursor.getInt(5));
                details.setMonth(cursor.getInt(6));
                details.setYear(cursor.getInt(7));
                details.set_ID1(cursor.getInt(0));
                list.add(details);
            }while (cursor.moveToPrevious());
        }
        return list;
    }

}
