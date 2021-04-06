package com.example.prayertime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView times;
    TimeViewAdapter adapter;
    ArrayList<PrayerTime> list;
    DatabaseHelper db;

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
        String email = mySharedPreferences.getString("USERNAME","");

        System.out.println(email);

        list = new ArrayList<PrayerTime>();
        list = db.getTimes(email);


        adapter = new TimeViewAdapter(MainActivity.this,list);
        times.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    private void setUpData(){

        SharedPreferences mySharedPreferences = this.getSharedPreferences("MYPREFERENCENAME", Context.MODE_PRIVATE);
        String email = mySharedPreferences.getString("USERNAME","");

        db = new DatabaseHelper(this);
        //default data
        db.insertPrayerTimeData(email,"Fajer","4:20 AM");
        db.insertPrayerTimeData(email,"Dhuhr","11:56 AM");
        db.insertPrayerTimeData(email,"Asr","3:24 PM");
        db.insertPrayerTimeData(email,"Maghreb","6:11 PM");
        boolean res = db.insertPrayerTimeData(email,"Isha","7:41 PM");
        

    }

}