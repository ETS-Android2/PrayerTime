package com.example.prayertime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getEmail = email.getText().toString();
                String getPassword = password.getText().toString();
                String getRePassword = rePassword.getText().toString();

                if(getEmail.equals("") || getPassword.equals("") || getRePassword.equals(""))
                    Toast.makeText(RegisterActivity.this,"Please enter all the fields!", Toast.LENGTH_LONG).show();
                else{
                    if(getPassword.equals(getRePassword)){
                        Boolean checkEmail = myDb.checkEmail(getEmail);
                        if(checkEmail==false){
                            Boolean insert = myDb.insertData(getEmail,getPassword);
                            if(insert==true){
                                Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }

            }
        });
        haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this,loginActivity.class);
                startActivity(i);
            }
        });
    }

    private void Initialisation() {
        myDb = new DatabaseHelper(this);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        rePassword = findViewById(R.id.validatePassword);
        register = findViewById(R.id.registerButton);
        haveAccount = findViewById(R.id.haveAccount);
    }
}