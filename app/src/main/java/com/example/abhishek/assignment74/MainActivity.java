package com.example.abhishek.assignment74;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mydb;
    TableLayout tableLayout;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        tableLayout=(TableLayout)findViewById(R.id.tablelayout);

        mydb = new DatabaseHelper(this);
        viewAll();
    }

    public void viewAll() {

        Cursor res = mydb.getAllData();
        if(res.getCount() == 0) {
            // show message
           // showMessage("Error","Nothing found");
            return;
        }

        // Add header row
        TableRow rowHeader = new TableRow(context);
        rowHeader.setBackgroundColor(Color.parseColor("#c0c0c0"));
        rowHeader.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        String[] headerText={"ID","NAME","SURNAME","MARKS"};
        for(String c:headerText) {
            TextView tv = new TextView(this);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(18);
            tv.setPadding(5, 5, 5, 5);
            tv.setText(c);
            rowHeader.addView(tv);
        }
        tableLayout.addView(rowHeader);


        while (res.moveToNext()) {

            String outlet_id= res.getString(0);
            String outlet_name= res.getString(1);
            String outlet_surname= res.getString(2);
            String outlet_marks= res.getString(3);


            // dara rows
            TableRow row = new TableRow(context);
            row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
            String[] colText={outlet_id+"",outlet_name,outlet_surname,outlet_marks};

            for(String text:colText) {
                TextView tv = new TextView(this);
                tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv.setGravity(Gravity.CENTER);
                tv.setTextSize(16);
                tv.setPadding(5, 5, 5, 5);
                tv.setText(text);
                row.addView(tv);
            }
            tableLayout.addView(row);

            // buffer.append("Marks :"+ res.getString(3)+"\n");
        }

       // tableLayout.
        // Show all data
        //showMessage("Data",buffer.toString());
    }
}