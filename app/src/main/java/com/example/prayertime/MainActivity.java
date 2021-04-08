package com.example.prayertime;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.LinearLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    RecyclerView times;
    TimeViewAdapter adapter;
    ArrayList<PrayerTime> list;
    DatabaseHelper db;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Button buttonOne = findViewById(R.id.button2);
//
//        buttonOne.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent i=new Intent(MainActivity.this,UserAccount.class);
//                startActivity(i);
//            }
//
//        });

        setUpData();

        times = findViewById(R.id.timesView);
        times.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences mySharedPreferences = this.getSharedPreferences("MYPREFERENCENAME", Context.MODE_PRIVATE);
        String email = mySharedPreferences.getString("USERNAME", "");

        list = new ArrayList<PrayerTime>();
        list = db.getTimes(email);

        Long current = Calendar.getInstance().getTime().getTime();


        for (int i = 0; i < list.size(); i++) {
            SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
            try {
                Date timed = timeFormat.parse(list.get(i).getTime());
                Long time = timed.getTime();

                if (time > current) {
                    System.out.println("yes" + time);
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        adapter = new TimeViewAdapter(MainActivity.this, list);
        times.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        Button settings = findViewById(R.id.settingButton);

        settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AppSettingsActivity.class);
                startActivity(i);
            }
        });

        PreferenceManager.setDefaultValues(this, R.xml.prefrences, false);




    }

    private void setUpData() {

        SharedPreferences mySharedPreferences = this.getSharedPreferences("MYPREFERENCENAME", Context.MODE_PRIVATE);
        String email = mySharedPreferences.getString("USERNAME", "");

        db = new DatabaseHelper(this);
        //default data
        db.insertPrayerTimeData(email, "Fajer", "4:20 AM");
        db.insertPrayerTimeData(email, "Dhuhr", "11:56 AM");
        db.insertPrayerTimeData(email, "Asr", "3:24 PM");
        db.insertPrayerTimeData(email, "Maghreb", "6:11 PM");
        boolean res = db.insertPrayerTimeData(email, "Isha", "7:41 PM");


    }

}