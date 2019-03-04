package com.example.helthyme;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button mainButton;
    private Button updateButton;
    private static String TAG = "[Add]";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainButton = (Button) findViewById(R.id.main_button);
        updateButton = (Button) findViewById(R.id.update_button);

        updateButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                System.out.println("updateButton clicked!");
                // To access the database, instantiate your subclass of SQLiteOpenHelper
                DataDBHelper dbHelper = new DataDBHelper(getBaseContext());
                readData(dbHelper);
            }
        });

        // Delete Data entries
        DataDBHelper dbHelper = new DataDBHelper(getBaseContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.onUpgrade(db,1,1);


        mainButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Not good, bro!", Toast.LENGTH_SHORT).show();
                // Put data in database _______________

                // To access the database, instantiate your subclass of SQLiteOpenHelper
                DataDBHelper dbHelper = new DataDBHelper(getBaseContext());

                // Gets the data repository in write mode
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                // Get current time
                DateFormat df = new SimpleDateFormat("d/MM/yyyy");
                String date = df.format(Calendar.getInstance().getTime());
                System.out.println(date);

                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                values.put(Data.DataEntry.COLUMN_NAME_DATE, date);
                values.put(Data.DataEntry.COLUMN_NAME_COUNT, 1);

                // Insert the new row, returning the primary key value of the new row
                long newRowId = db.insert(Data.DataEntry.TABLE_NAME, null, values);


                System.out.println(TAG + " New item added!");
            }

        });
    }

    public void readData(DataDBHelper dbHelper){

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                Data.DataEntry.COLUMN_NAME_DATE,
                Data.DataEntry.COLUMN_NAME_COUNT,
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = Data.DataEntry.COLUMN_NAME_DATE + " = ?";
        String[] selectionArgs = { "4/03/2019" };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                Data.DataEntry.COLUMN_NAME_COUNT + " ASC";

        Cursor cursor = db.query(
                Data.DataEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(Data.DataEntry._ID));
            String itemDate = cursor.getString( cursor.getColumnIndex("date") );
            itemIds.add(itemId);
            System.out.println(itemDate);
        }
        cursor.close();


    }

}
