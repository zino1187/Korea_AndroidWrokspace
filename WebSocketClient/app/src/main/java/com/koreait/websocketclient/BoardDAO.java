package com.koreait.websocketclient;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;

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
import java.util.List;

public class BoardDAO {
    String TAG=this.getClass().getName();
    String ip="172.30.1.8";
    int port=7777;
    Gson gson=new Gson();
    MainActivity mainActivity;

    public BoardDAO(MainActivity mainActivity){
        this.mainActivity=mainActivity;
    }

    //목록
    public void selectAll() throws BoardUpdateException{
        String uri="/board";
        BufferedReader buffr=null;
        try {
            URL url = new URL("http://"+ip+":"+port+uri);
            HttpURLConnection con=(HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            //연결이 끊어지기 전에 스트림으로 데이터 가져오기
            buffr = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String data=null; //한줄을 받을 임시 데이터

            while(true){
                data = buffr.readLine();
                if(data==null)break;
                sb.append(data);
            }
            int code = con.getResponseCode();//요청 및 응답
            if(code!=200){
                throw new BoardUpdateException("목록 조회 실패");
            }
            //제이슨을 파싱하여, 자바의 객체화..
            Log.d(TAG, "sb.toString() = "+sb.toString());

            try {
                JSONObject jsonObject = new JSONObject(sb.toString());
                JSONArray jsonArray = (JSONArray) jsonObject.get("data");
                //jsonArray --> Java Object List
                ArrayList<Board> boardList = new ArrayList<Board>();

                for(int i=0;i<jsonArray.length();i++ ){
                    JSONObject json=(JSONObject) jsonArray.get(i);
                    Board board = gson.fromJson(json.toString(), Board.class);
                    Log.d(TAG, "writer is "+board.getWriter());
                    boardList.add(board);
                }
                Log.d(TAG, "리스트 사이즈는 "+boardList.size());
                //UI 갱신을 핸들러에 부탁하자
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("boardList", boardList);
                message.setData(bundle);
                mainActivity.handler.sendMessage(message);

            } catch (JSONException e) {
                e.printStackTrace();
            }
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
    }

    //상세보기


    //등록


    //수정


    //삭제
}
