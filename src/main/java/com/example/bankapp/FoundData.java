package com.example.bankapp;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
//This is usefull to fecth datas online
public  class FoundData extends AsyncTask<Void,Void,Void> {

    String data = "";
    String dataParsed = "";
    String singleParsed = "";
    //We are going to stock the informations in the BuildConfig
    private static final String API_KEY = BuildConfig.ApiKey;

    @Override
    protected Void doInBackground(Void... voids) {
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
                //System.out.println((dataParsed));
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Log.e("Exchange-JSON", "Cannot found http server", e);
        }
        return null;
    }
//Execution
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity2.textViewAmount.setText(dataParsed);
    }
}
