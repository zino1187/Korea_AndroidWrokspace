package com.study.app01092;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncManager extends AsyncTask<URL, Integer , String> {
    String TAG=this.getClass().getName();
    MainActivity mainActivity;

    public AsyncManager(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    protected void onPreExecute() {
        Log.d(TAG, "onPreExecute");
    }

    @Override
    protected String doInBackground(URL... urls) {

        Log.d(TAG, "doInBackground "+ urls[0]);

        BufferedReader buffr=null;
        StringBuilder sb = new StringBuilder();

        try {
            HttpURLConnection con=(HttpURLConnection) urls[0].openConnection();
            con.connect();
            con.setRequestMethod("GET");
            int code=con.getResponseCode();
            String msg=con.getResponseMessage();
            buffr = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while(true){
                String data = buffr.readLine();
                Log.d(TAG, "data =  "+ data);
                if(data==null)break;
                sb.append(data);
            }

            Log.d(TAG, "doInBackground "+ sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(buffr!=null){
                try {
                    buffr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    @Override
    protected void onPostExecute(String data) {
        mainActivity.getList(data);

        Log.d(TAG, "onPostExecute: "+ data);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        Log.d(TAG, "onProgressUpdate");
    }

    @Override
    protected void onCancelled(String data) {
        Log.d(TAG, "onCancelled");
    }
}
