package com.example.prayertime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class LauncherActivity extends Activity {
    Handler handler;
    Button regButton, logButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

//        handler=new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent=new Intent(LauncherActivity.this,MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        },1000);
        regButton = findViewById(R.id.regButton);
        logButton = findViewById(R.id.logButton);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LauncherActivity.this,RegisterActivity.class);
                startActivity(i);
            }//comment
        });

        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LauncherActivity.this,loginActivity.class);
                startActivity(i);
            }
        });


    }
}