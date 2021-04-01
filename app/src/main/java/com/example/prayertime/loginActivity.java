package com.example.prayertime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class loginActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText email2, pass2;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Initialisation();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(loginActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void Initialisation() {
        myDb = new DatabaseHelper(this);
        email2 = findViewById(R.id.email2);
        pass2 = findViewById(R.id.pass2);
        login = findViewById(R.id.loginButton);
    }
}