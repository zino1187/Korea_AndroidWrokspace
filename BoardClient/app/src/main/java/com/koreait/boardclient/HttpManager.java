package com.koreait.boardclient;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpManager {
    String TAG=this.getClass().getName();
    URL url;
    HttpURLConnection con;
    BoardConverter<Board> converter;

    public HttpManager(){
        converter = new BoardConverter<Board>();
    }

    public ArrayList<Board> requestByGet(String requestUrl){
        BufferedReader buffr=null;
        StringBuilder sb = new StringBuilder();
        ArrayList<Board> boardList=null;

        try {
            url = new URL(requestUrl);
            con=(HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            //서버의 제이슨 데이터를 가져오기 위한 스트림 생성
            buffr=new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
            //스트림으로 데이터를 누적 최종적으로 json 문자열화 시키자
            String data=null;
            while(true){
                data=buffr.readLine();
                if(data==null)break;
                sb.append(data);
            }
            con.getResponseCode(); //요청 및 응답이 발생하는 시점!!!
            Log.d(TAG, sb.toString());

            boardList=converter.getConvertedData(sb.toString());
            Log.d(TAG, "컨버트된 리스트의 수 "+boardList.size());

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
        return boardList;
    }

    public void requestByPost(String requestUrl, String param){
        URL url=null;
        HttpURLConnection con=null;
        BufferedWriter buffw=null;

        try {
            url = new URL(requestUrl);
            con=(HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type","application/json;charset=utf-8");//헤더값 구성
            con.setDoOutput(true);//서버로 데이터를 출력하려면 true 로 처리해야 함

            buffw = new BufferedWriter(new OutputStreamWriter( con.getOutputStream(),"UTF-8"));
            buffw.write(param);//json 문자열 전송!!
            buffw.flush();

            int code = con.getResponseCode(); //여기서 요청 및 응답처리됨..
            Log.d(TAG, "'등록 후 서버로부터 받은 응답코드 "+code);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(buffw!=null){
                try {
                    buffw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
