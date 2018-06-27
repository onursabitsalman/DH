package com.example.onurs.donanmhaber;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    HttpHandler httpHandler;
    ProgressDialog progressDialog;
    ArrayList<ItemData> dataArrayList;
    ListView list;
    ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataArrayList = new ArrayList<>();
        list = findViewById(R.id.listView);

        itemAdapter = new ItemAdapter(this,dataArrayList);

        new getValues().execute();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Log.v("pozii", String.valueOf(position));
                Log.v("pozii2", dataArrayList.get(position).getDataUrl());
                Intent intent = new Intent(MainActivity.this, WebConnection.class);
                String message = dataArrayList.get(position).getDataUrl();
                intent.putExtra("url", message);
                startActivity(intent);
            }
        });







    }

    private class getValues extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute(){  //işlem başladığında
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid){  //işlem tamamlandıgında
            super.onPostExecute(aVoid);
            if (progressDialog.isShowing()){
                progressDialog.dismiss();

            }

            list.setAdapter(itemAdapter);
        }

        @Override
        protected Void doInBackground(Void... voids) {  //işlemin gerçekleşme zamanında

            httpHandler = new HttpHandler();
            String jsonString = httpHandler.makeServiceCall("https://api.donanimhaber.com/prod/and/api/BscNews");

            if (jsonString != null){
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(jsonString);
                    JSONArray allData = jsonObject.getJSONArray("Data");



                    for (int i=0; i<allData.length(); i++){
                        JSONObject data = allData.getJSONObject(i);

                        //to get image value
                        JSONObject imageData = data.getJSONObject("Image");
                        String imageValue = imageData.getString("Value");

                        //to get title
                        String dataTitle = data.getString("Title");

                        //to get total read
                        int totalRead = data.getInt("TotalRead");

                        //to get text color
                        String textColor = data.getString("TextColor");

                        //to get color avarage
                        String colorAvarage = data.getString("ColorAvarage");

                        //to get url
                        String dataUrl = data.getString("Url");

                        ItemData itda = new ItemData(imageValue,dataTitle,totalRead,textColor,colorAvarage,dataUrl);
                        dataArrayList.add(itda);

                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                Log.d("JSON DURUMU","BOŞ GELDİ!");
            }

            return null;
        }
    }
}
