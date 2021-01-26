package com.study.app01092;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConnectionThread extends Thread{
    String TAG=this.getClass().getName();

    protected String getData(URL... urls) {

        Log.d(TAG, "doInBackground "+ urls[0]);

        BufferedReader buffr=null;
        StringBuilder sb = new StringBuilder();

        try {
            HttpURLConnection con=(HttpURLConnection) urls[0].openConnection();
            con.setRequestMethod("GET");
            //con.connect();
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

    public void run() {
        URL url = null;
        try {
            url = new URL("http://192.168.35.96:8888/rest/cs/qna/list");
            getData(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
