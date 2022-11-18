package com.example.dailyexpenses;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dailyexpenses.context.dailyUpdate_context;
import com.example.dailyexpenses.data.InfoReaderDbHelper;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.color.MaterialColors;

import java.util.ArrayList;
import java.util.List;

public class graph extends Fragment {
    Context gcontext;
    BarChart bar;
    Spinner graph_spinner;
    EditText graph_edit;
    Button graph_show;
    LineChart lineChart;
    int n;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        gcontext=getContext();
        return inflater.inflate(R.layout.fragment_graph, container,false );
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bar=view.findViewById(R.id.barchart);
        graph_spinner=view.findViewById(R.id.graph_spinner);
        graph_edit=view.findViewById(R.id.graph_edit);
        graph_show=view.findViewById(R.id.graph_show);
        lineChart=view.findViewById(R.id.line_chart);


         // spinner
        graph_edit.setInputType(InputType.TYPE_CLASS_NUMBER);
        graph_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(graph_spinner.getSelectedItem().toString().matches("Yearly")){
                    graph_edit.setHint("ENTER YEAR");
                    graph_edit.setInputType(InputType.TYPE_CLASS_NUMBER);
                }
                else {
                    graph_edit.setHint("ENTER MM-YYYY");
                    graph_edit.setInputType(InputType.TYPE_CLASS_TEXT);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //button show

        ArrayList<BarEntry> info=new ArrayList<>();
        graph_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteOpenHelper infoReaderDbHelper = new InfoReaderDbHelper(gcontext);
                SQLiteDatabase db = infoReaderDbHelper.getReadableDatabase();
                Cursor cursor1;
                if(graph_spinner.getSelectedItem().toString().matches("Yearly")){

                    cursor1=db.query("dailyUpdate", new String[]{"Month","Year","SUM(Price)"},
                            "Year=?",new String[] {String.valueOf(graph_edit.getText())},"Month",
                            null,"Month ASC ",null);


                    info.clear();
                    int i1=1;
                    if(cursor1.moveToFirst()) {
                        do {
                            for (int i = i1; i < cursor1.getInt(0); i++) {
                                info.add(new BarEntry(i, 0));
                            }
                            info.add(new BarEntry(cursor1.getInt(0), cursor1.getInt(2)));
                            i1 = cursor1.getInt(0) + 1;
                        } while (cursor1.moveToNext());
                        for (int i = i1; i <= 12; i++)
                            info.add(new BarEntry(i, 0));


                        BarDataSet barDataSet = new BarDataSet(info, "MONTH");
                        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                        barDataSet.setValueTextColor(android.R.color.black);
                        barDataSet.setValueTextSize(20f);

                        BarData barData = new BarData(barDataSet);
                        bar.setFitBars(true);
                        bar.setData(barData);
                        bar.getDescription().setText("MONTHLY REPORT");
                        bar.animateY(4000);
                        LineDataSet lineDataSet = new LineDataSet((List) info, "MONTH");
                        lineDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                        lineDataSet.setValueTextColor(android.R.color.black);
                        lineDataSet.setValueTextSize(20f);

                        LineData lineData = new LineData(lineDataSet);
                        lineChart.setData(lineData);
                        lineChart.getDescription().setText("MONTHLY REPORT");
                        lineChart.animateY(8000);
                    }

                }
                else{
                    cursor1=db.query("dailyUpdate", new String[]{"Date","SUM(Price)","Day","Month","Year"},
                                        " Month=? AND Year=?",new String[] {graph_edit.getText().toString().substring(0, 2),
                            graph_edit.getText().toString().substring(3, 7)},"Date",null,"Date ASC ",null);

                                //providing data to graph


                     info.clear();
                    int i1=1;
                    if(cursor1.moveToFirst()){
                        if(cursor1.getInt(2)%2==1)
                            n=31;
                        else
                            n=30;
                            do{
                                for(int i=i1;i<cursor1.getInt(2);i++) {
                                info.add(new BarEntry(i, 0));
                            }
                                info.add(new BarEntry(cursor1.getInt(2), cursor1.getInt(1)));
                                Log.d("countfaizuu",info.toString());
                                i1=cursor1.getInt(2)+1;
                            }while (cursor1.moveToNext());
                        for(int i=i1;i<=n;i++)
                            info.add(new BarEntry(i, 0));


                        BarDataSet barDataSet=new BarDataSet(info,"MONTH");
                        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                        barDataSet.setValueTextColor(android.R.color.black);
                        barDataSet.setValueTextSize(20f);

                        BarData barData= new BarData(barDataSet);
                        bar.setFitBars(true);
                        bar.setData(barData);
                        bar.getDescription().setText("MONTHLY REPORT");
                            bar.animateY(4000);
                        LineDataSet lineDataSet=new LineDataSet((List) info,"MONTH");
                        lineDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                        lineDataSet.setValueTextColor(android.R.color.black);
                        lineDataSet.setValueTextSize(20f);

                        LineData lineData= new LineData(lineDataSet);
                        lineChart.setData(lineData);
                        lineChart.getDescription().setText("MONTHLY REPORT");
                        lineChart.animateY(8000);



                        }







                }

            }
        });

    }
}
