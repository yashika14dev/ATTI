package com.scratch.automatedtt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button login;
    private int counter=5;
    TextView info;
    EditText loginId;
    EditText loginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button) findViewById(R.id.loginbtn);
         info = (TextView) findViewById(R.id.loginInfo_tv);
       loginId = (EditText) findViewById(R.id.loginId_et);
       loginPassword = (EditText) findViewById(R.id.loginPassword_et);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validuser(loginId.getText().toString() , loginPassword.getText().toString());
            }
        });
    }

    private void validuser(String username , String userpassword){
            if((username.equals("Admin"))&&(userpassword.equals("1234"))){

                Intent i = new Intent(MainActivity.this, Home.class);
                startActivity(i);
                Toast.makeText(this, "Logged in as Admin ", Toast.LENGTH_SHORT).show();
            }
            else if ((username.equals("Faculty"))&&(userpassword.equals("9876"))){
                Intent i = new Intent(MainActivity.this, FacultyHome.class);
                startActivity(i);
                Toast.makeText(this, "Logged in as a Faculty ", Toast.LENGTH_SHORT).show();
            }
            else{
                counter-- ;
                info.setText("Enter a valid UserID or Password");
                loginId.setText("");
                loginPassword.setText("");
                Toast.makeText(this, "No. of attempts remaining : "+ counter, Toast.LENGTH_SHORT).show();
                if(counter==0){
                    info.setText("User not Authorized");
                    login.setEnabled(false);
                }

            }
        }
    }

