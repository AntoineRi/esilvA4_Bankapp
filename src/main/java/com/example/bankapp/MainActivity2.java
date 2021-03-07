package com.example.bankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//import com.owlike.genson.Genson;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;


import java.net.HttpURLConnection;

import java.io.FileNotFoundException;
//This is the class for the second page, the page where we see all datas
public class MainActivity2 extends AppCompatActivity {
//Initializing :
    public static TextView textViewAmount;
    private static final String FILE_NAME = "data.txt";
    Button button1;
    Button button2;
    Button refreshbutton;
    String data = "";
    String dataParsed = "";
    String singleParsed = "";
    //The line below is to hide the API Key using graddle.properties
    private static final String API_KEY = BuildConfig.ApiKey;

//Write the files
    public void writeFile(String data) throws IOException {

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(FILE_NAME, MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
            //a toast is a non-blocking information message
            Toast toast = Toast.makeText(MainActivity2.this, "File filled", Toast.LENGTH_LONG);
            toast.show();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
            Toast toast = Toast.makeText(MainActivity2.this, "doesn't work", Toast.LENGTH_LONG);
            toast.show();
        }
    }
//Import datas from the api
    public void importData() {
        try {
            URL url = new URL(API_KEY);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";

            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }
            JSONArray JA = new JSONArray(data);
            for (int i = 0; i < JA.length(); i++) {
                JSONObject JO = (JSONObject) JA.get(i);
                singleParsed = "Id:" + JO.get("id") + "\n" + "Account_Name:" + JO.get("accountName") + "\n" + "Amount:" + JO.get("amount") + "\n" + "iban:" + JO.get("iban") + "\n" + "currency:" + JO.get("currency") + "\n";

                dataParsed = dataParsed + singleParsed;
            }
        } catch (Exception e) {
            Log.e("Exchange-JSON", "Cannot found http server", e);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //We are calling the FoundData class wich allow us to fetch data in the APi
        FoundData process = new FoundData();
        process.execute();

        textViewAmount = findViewById(R.id.textView5);
        //Scrolling is usefull to display all the informations in a same page
        textViewAmount.setMovementMethod(new ScrollingMovementMethod());
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        refreshbutton = (Button) findViewById(R.id.refreshbutton);

        textViewAmount.setText(dataParsed);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = textViewAmount.getText().toString();
                try {
                    writeFile(text);
                    Toast toast = Toast.makeText(MainActivity2.this, "File saved", Toast.LENGTH_LONG);
                    toast.show();
                }

                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileInputStream fis = null;

                try {
                    fis = openFileInput(FILE_NAME);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    StringBuilder sb = new StringBuilder();
                    String text;

                    while((text = br.readLine()) != null){
                        sb.append(text).append("\n");

                    }
                    textViewAmount.setText(sb.toString());
                    Toast toast = Toast.makeText(MainActivity2.this, "File Loaded", Toast.LENGTH_LONG);
                    toast.show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally{
                    if ( fis != null){
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });

        refreshbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
    }



    protected void onResume(){
        super.onResume();

    }
}