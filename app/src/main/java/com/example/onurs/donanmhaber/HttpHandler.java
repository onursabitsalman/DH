package com.example.onurs.donanmhaber;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpHandler {
    public HttpHandler(){}

    public String makeServiceCall(String requestUrl){
        String response = null;


        try {

            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(connection.getInputStream());
            response = convertStreamToString(in);

        }catch (MalformedURLException e){

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    private String convertStreamToString(InputStream in) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line = "";
        try {
            while ((line = reader.readLine()) != null){
                sb.append(line).append("\n");
            }
        }catch (IOException e){

        }finally {
            try {
                in.close();
            }catch (IOException e){

            }

        }

        return sb.toString();
    }
}
