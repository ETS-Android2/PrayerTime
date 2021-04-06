package com.example.prayertime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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

        times = findViewById(R.id.timesView);
        times.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<PrayerTime>();

        PrayerTime t = new PrayerTime("Fajer","4:30");
        PrayerTime t2 = new PrayerTime("Fajer","4:80");
        list.add(t);
        list.add(t2);
        list.add(t2);
        list.add(t);

        adapter = new TimeViewAdapter(MainActivity.this,list);
        times.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }
}