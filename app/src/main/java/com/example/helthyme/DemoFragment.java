package com.example.helthyme;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

public class DemoFragment extends Fragment {

    public DemoFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout resource that'll be returned
        final View rootView = inflater.inflate(R.layout.fragment_demo, container, false);

        // Get the arguments that was supplied when the fragment was instantiated in the
        // CustomPagerAdapter
        Bundle args = getArguments();

        // Calendar
        CalendarView calendarView=(CalendarView) rootView.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                Toast.makeText(rootView.getContext(), ""+dayOfMonth, Toast.LENGTH_SHORT).show();// TODO Auto-generated method stub


            }
        });

        return rootView;
    }
}
