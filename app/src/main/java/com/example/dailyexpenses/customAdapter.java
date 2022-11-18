package com.example.dailyexpenses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dailyexpenses.Details.dailyDetails;
import com.example.dailyexpenses.data.InfoReaderDbHelper;

import java.util.ArrayList;
import java.util.List;

public class customAdapter extends ArrayAdapter<dailyDetails> {
    List<dailyDetails> list=new ArrayList<>();
    Context mcontext;
    int mresource;
    public customAdapter(@NonNull Context context,int textViewResourceId,List<dailyDetails> resource) {
        super(context,textViewResourceId,resource);
        mcontext=context;
        mresource=textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        View v=convertView;
        String getdate=getItem(position).getDate();
        String getcategpries=getItem(position).getCategories();
        int getprice=(getItem(position).getPrice());
        String getdescription=getItem(position).getDescription();
        int id=getItem(position).get_ID1();

        dailyDetails d=new dailyDetails(getdate,getcategpries,getdescription,getprice,id);
        LayoutInflater layoutInflater=LayoutInflater.from(mcontext);
        convertView=layoutInflater.inflate(mresource,parent,false);
        v=layoutInflater.inflate(R.layout.row,null);
        TextView date=v.findViewById(R.id.list_date);
        TextView price=v.findViewById(R.id.list_price);
        TextView categories=v.findViewById(R.id.list_categories);
        TextView description=v.findViewById(R.id.list_description);
        TextView id_=v.findViewById(R.id.list_id);
        date.setText(getdate);
        price.setText(String.valueOf(getprice+" ₹"));
        categories.setText(getcategpries);
        description.setText(getdescription);
        id_.setText(String.valueOf(id));
        return v;
    }
}
