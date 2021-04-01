package com.example.prayertime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText email, password, rePassword;
    Button register;
    TextView haveAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Initialisation();
    }

    private void Initialisation() {
        myDb = new DatabaseHelper(this);
    }
}