package com.koreait.actionbarapp.gallery;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

//제이쿼리의 JQuery ajax 처럼, 안드로이드에서도 웹서버와의 통신시
//자주 사용되는 비동기 객체가 지원된다...but.  2019말에 Deprecated..
//AsyncTask<비동기로 작업할때 사용될 파라미터 주로 url , 진행상황 데이터  , 응답데이터(웹서버응답데이터)>
public class MyAsync extends AsyncTask<String, Void, String> {
    String TAG=this.getClass().getName();
    GalleryFragment galleryFragment;

    public MyAsync(GalleryFragment galleryFragment){
        this.galleryFragment = galleryFragment;
    }

    //비동기 요청 전에 하고싶은 작업...(메인 쓰레드로 수행 : UI 제어가능)
    protected void onPreExecute() {
        super.onPreExecute();
    }
    //비동기 요청 (쓰레드로 수행 : UI제어 불가...)
    //주로 네트워크 요청시 사용됨..
    protected String doInBackground(String... params) {
        String requestUrl = params[0]; //웹서버 요청 주소
        BufferedReader buffr=null;
        StringBuilder sb=null;

        try {
            URL url = new URL(requestUrl);
            HttpURLConnection  con = (HttpURLConnection)url.openConnection();
            buffr  = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            sb = new StringBuilder();
            String data=null;

            while(true){
                data = buffr.readLine();
                if(data==null)break;
                sb.append(data);
            }
            con.getResponseCode(); //요청 후 응답받음.

        } catch (MalformedURLException e) {
            e.printStackTrace();
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

    //요청하는 중에 처리할 작업...(메인 쓰레드로 수행: UI 제어가능)
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
    //요청 후 받은 응답데이터 처리(메인 쓰레드로 수행: UI 제어가능)
    //doInBackground() 메서드에서의 반환값이 아래의
    // //onPostExecute() 메서드의인수로 전달 (시스템에 의해)
    protected void onPostExecute(String data) {
        Log.d(TAG, "background요청 후 전달받은 데이터는 "+data);

        //이 메서드는 메인쓰레드에 의해 수행되므로, 여기서 UI 도 제어할 수 있다..
        //즉 핸들러도 필요없다....

        //제이슨배열을 어댑터의 리스트에 넣자!!
        try {
            JSONArray jsonArray = new JSONArray(data);
            ArrayList<Gallery> galleryList = new ArrayList<Gallery>();//empty

            for(int i=0;i<jsonArray.length();i++){
                JSONObject json = (JSONObject) jsonArray.get(i);

                //VO에 옮겨담기
                Gallery gallery = new Gallery();
                gallery.setGallery_id(json.getInt("gallery_id"));
                gallery.setTitle(json.getString("title"));
                gallery.setFilename(json.getString("filename"));
                //웹서버에서 이미지 가져와서 비트맵으로 반환..
                ImageDownLoaderAsync downAsync = new ImageDownLoaderAsync(gallery, galleryFragment);
                downAsync.execute("http://172.30.1.28:7777/images/"+gallery.getFilename());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
