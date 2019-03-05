package com.example.helthyme;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button mainButton;
    private Button updateButton;
    CustomPagerAdapter mCustomPagerAdapter;
    ViewPager mViewPager;
    private DataManager dataManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataManager = new DataManager(getBaseContext());

        // == Setting up the ViewPager ==
        mCustomPagerAdapter = new CustomPagerAdapter(getSupportFragmentManager(), this);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);

        mainButton = (Button) findViewById(R.id.main_button);
        updateButton = (Button) findViewById(R.id.update_button);

        updateButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                System.out.println("updateButton clicked!");
                // To access the database, instantiate your subclass of SQLiteOpenHelper
                dataManager.readEntries("5/03/2019");
            }
        });

        mainButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Not good, bro!", Toast.LENGTH_SHORT).show();
                // Put data in database
                dataManager.addEntry();
            }
        });
    }
}
