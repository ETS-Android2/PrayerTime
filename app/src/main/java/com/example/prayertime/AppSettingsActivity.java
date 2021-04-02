package com.example.prayertime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AppSettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_settings);
        if (findViewById(R.id.fragment) != null && savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment, new SettingsFragment())
                    .commit();
        }
    }
}