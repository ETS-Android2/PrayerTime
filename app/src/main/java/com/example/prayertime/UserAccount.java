package com.example.prayertime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserAccount extends AppCompatActivity {
    String email;
    String pass;
    TextView emailprof;
    TextView passprof;
    Button logout;
    Button update;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);
        SharedPreferences mySharedPreferences = this.getSharedPreferences("MYPREFERENCENAME", Context.MODE_PRIVATE);
        email = mySharedPreferences.getString("USERNAME", "");
        pass = mySharedPreferences.getString("Password", "");
        emailprof=findViewById(R.id.emailprofilee);
        passprof=findViewById(R.id.passwordprofile);
        emailprof.setText(email);
        passprof.setText(pass);
        myDb = new DatabaseHelper(this);
        logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finishAffinity();
                Intent i=new Intent(UserAccount.this,loginActivity.class);///write the login activity name
                startActivity(i);
            }
        });
        update=findViewById(R.id.saveupdate);
        update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView emailnew=findViewById(R.id.emailprofilee);//change
                TextView passnew=findViewById(R.id.passwordprofile);//change name
                String emaillnew1=emailnew.getText().toString();
                String passinnew=passnew.getText().toString();
                if(email.equals(emaillnew1)){
                    if(passinnew.equals(pass)){
                        Toast.makeText(UserAccount.this, "The  information is same !! ", Toast.LENGTH_SHORT).show();

                    return;}
                    else{
                        myDb.UpdateAcount( email, passinnew,email); //update old user new pass
                        Toast.makeText(UserAccount.this, "change information !! ", Toast.LENGTH_SHORT).show();

                    }
                }
                else {
                    /////////cheak email find
                    if(myDb.checkEmail(emaillnew1))
                        Toast.makeText(UserAccount.this, "This email already exist! ", Toast.LENGTH_SHORT).show();
                        //////////save it to gather
                    else{
                        myDb.UpdateAcount(emaillnew1,passinnew,email);
                    Toast.makeText(UserAccount.this, "change information ! ", Toast.LENGTH_SHORT).show();


                }}

            }
        });
    }
}