package com.example.bankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//This is the class for the login page
public class MainActivity extends AppCompatActivity {
//we initialize the buttons
    private Button buttonlogin;
    private String Name = "user";
    private String Password = "password";

    private int counter = 3;

//We are going to set up the graphical interface and initialize the variables
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //connection to the xml file


//We set up the login button, and if it's okay we redirect to the MainActivity2 page
        final Button button1 = findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText textview = findViewById(R.id.editTextTextPassword);
                EditText textview2 = findViewById(R.id.editTextTextPassword2);
                String inputusername = textview.getText().toString();
                String inputpassword = textview2.getText().toString();
//If empty
                if (inputusername.isEmpty() || inputpassword.isEmpty()){
                    Toast.makeText(MainActivity.this, "You have to enter your username and your password",  Toast.LENGTH_LONG).show();
                }
                else{
                    validate(inputusername,inputpassword);
                }
            }
        });
    }
//If everything is good
    private void validate(String name, String password){
        if (name.equals(Name) && password.equals(Password)){
            Toast.makeText(MainActivity.this, "login successfuly",  Toast.LENGTH_SHORT).show();
            openActivity2();
        }
        else{
            counter--;
            Toast.makeText(MainActivity.this, "Oh, I think you made a mistake in your username or password",  Toast.LENGTH_LONG).show();
            if(counter ==0){
                buttonlogin.setEnabled(false);
            }

        }

    }
//Redirection
    public void openActivity2() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}