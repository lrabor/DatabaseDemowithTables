/*
 * Copyright (c) 2016. All Rights Reserved.
 */

package com.rabor.databasedemowithtables;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class DisplayListActivity extends AppCompatActivity {

    // define the widget variable layout
    private TableLayout table_layout;

    // define SQL controller variable
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list);

        // navigate up to the parent class
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // get reference to the layout widget
        table_layout = (TableLayout) findViewById(R.id.tableLayout1);

        // instantiate the handler constructor
        dbHandler = new MyDBHandler(this);

        // call the build table method
        BuildTable();
    }

    // responsible for building the table
     private void BuildTable() {

        dbHandler.open();
        Cursor c = dbHandler.readEntry();

        int rows = c.getCount();
        int cols = c.getColumnCount();

        c.moveToFirst();

        // outer for loop
        for (int i = 0; i < rows; i++) {

            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            // inner for loop
            for (int j = 0; j < cols; j++) {

                TextView tv = new TextView(this);
                tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv.setBackgroundResource(R.drawable.cell_shape);
                tv.setGravity(Gravity.CENTER);
                tv.setTextSize(18);
                tv.setPadding(0, 5, 0, 5);

                tv.setText(c.getString(j));

                row.addView(tv);

            }

            c.moveToNext();

            table_layout.addView(row);

        }

        // close the database
        dbHandler.close();
    }

}
