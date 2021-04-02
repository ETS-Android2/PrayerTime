package com.example.prayertime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
                String email = email2.getText().toString();
                String password = pass2.getText().toString();

                if(email.equals("") || password.equals(""))
                    Toast.makeText(loginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkEmailPass = myDb.checkEmailandPassword(email,password);
                    if(checkEmailPass == true){
                        SetAccountUser();
                        Toast.makeText(loginActivity.this, "Sign in successfully", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(loginActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void Initialisation() {
        myDb = new DatabaseHelper(this);
        email2 = findViewById(R.id.email2);
        pass2 = findViewById(R.id.pass2);
        login = findViewById(R.id.loginButton);
    }
    public void SetAccountUser(){
        TextView email2=findViewById(R.id.email2);//change
        TextView pass2=findViewById(R.id.pass2);//change name
        String emaill=email2.getText().toString();
        String passin=pass2.getText().toString();


        SharedPreferences mySharedPreferences = this.getSharedPreferences("MYPREFERENCENAME", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString("USERNAME",emaill);
        editor.putString("Password",passin);
        editor.apply();


    }
}