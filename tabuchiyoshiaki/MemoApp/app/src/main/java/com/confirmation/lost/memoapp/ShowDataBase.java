package com.confirmation.lost.memoapp;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShowDataBase extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_database);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);

        MyOpenHelper helper = new MyOpenHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();


        Cursor c = db.query("memo", new String[] { "work_content", "remarks"}, null,
                null, null, null, null);

        boolean mov = c.moveToFirst();
        while (mov) {
            TextView textview = new TextView(this);
            textview.setText(String.format("%s : %s", c.getString(0),
                    c.getString(1)));
            mov = c.moveToNext();
            layout.addView(textview);
        }
        c.close();
        db.close();
    }
}