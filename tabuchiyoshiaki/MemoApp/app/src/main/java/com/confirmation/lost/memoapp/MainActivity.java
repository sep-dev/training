package com.confirmation.lost.memoapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyOpenHelper helper = new MyOpenHelper(this);
        final SQLiteDatabase db = helper.getWritableDatabase();

        final EditText workContentText = (EditText) findViewById(R.id.editWorkContent);
        final EditText remarksText = (EditText) findViewById(R.id.editRemarks);

        Button entryButton = (Button) findViewById(R.id.insert);
        entryButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String workContent = workContentText.getText().toString();
                String remarks = remarksText.getText().toString();

                ContentValues insertValues = new ContentValues();
                insertValues.put("workContent", workContent);
                insertValues.put("remarks", remarks);
                long id = db.insert("memo", workContent, insertValues);

            }
        });
        Button updateButton = (Button) findViewById(R.id.update);
        updateButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String workContent = workContentText.getText().toString();
                String remarks = remarksText.getText().toString();

                if (workContent.equals("")) {
                    Toast.makeText(MainActivity.this, "用件を入力してください。",
                            Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues updateValues = new ContentValues();
                    updateValues.put("remarks", remarks);
                    db.update("memo", updateValues, "workContent=?", new String[]{workContent});
                }
            }
        });
        Button deleteButton = (Button) findViewById(R.id.delete);
        deleteButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String workContent = workContentText.getText().toString();
                String remarks = remarksText.getText().toString();


                if (workContent.equals("")) {
                    Toast.makeText(MainActivity.this, "用件を入力してください。",
                            Toast.LENGTH_SHORT).show();
                } else {
                    db.delete("memo", "workContent=?", new String[]{workContent});
                }
            }
        });
        Button deleteAllButton = (Button) findViewById(R.id.deleteAll);
        deleteAllButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String workContent = workContentText.getText().toString();
                String remarks = remarksText.getText().toString();

                db.delete("memo", null, null);
            }
        });
        Button dataBaseButton = (Button) findViewById(R.id.dataBase);
        dataBaseButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dbIntent = new Intent(MainActivity.this,
                        ShowDataBase.class);
                startActivity(dbIntent);
            }
        });
    }
}
