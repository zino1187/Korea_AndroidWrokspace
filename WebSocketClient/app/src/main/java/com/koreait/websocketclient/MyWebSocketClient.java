package com.koreait.websocketclient;

import android.util.Log;

import com.google.gson.Gson;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;
import org.java_websocket.drafts.Draft;
import org.json.JSONException;
import org.json.JSONObject;

public class MyWebSocketClient extends WebSocketClient {
    String TAG=this.getClass().getName();
    MainActivity mainActivity;
    Gson gson=new Gson();

    public MyWebSocketClient(URI serverUri, MainActivity mainActivity) {
        super(serverUri);
        this.mainActivity=mainActivity;
    }

    //서버와 연결되면...
    public void onOpen(ServerHandshake handshakedata) {
        Log.d(TAG, "onOpen called");
    }

    //메시지가 도착하면..
    public void onMessage(String message) {

        //서버에서 전달되어 온 메시지 분석
        //서버에서 발생할 일이? 등록? 수정? 삭제?
        try {
            JSONObject json = new JSONObject(message); //string --> json object

            if(json.get("requestCode").equals("create")){ //서버에서 등록작업이 누군가에 의해 발생함...
                //목록을 갱신
                mainActivity.boardDAO.selectAll();
                Log.d(TAG, "글쓰기 발생 ");
            }else if(json.get("requestCode").equals("update")){
                //목록을 갱신
                mainActivity.boardDAO.selectAll();
                Log.d(TAG, "수정 발생 ");
            }else if(json.get("requestCode").equals("delete")){
                //목록을 갱신
                mainActivity.boardDAO.selectAll();
                Log.d(TAG, "삭제 발생 ");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //메시지 보내기
    public void sendMsg(SocketMessage socketMessage){
        String jsonString = gson.toJson(socketMessage);
        this.send(jsonString);
    }

    //접속이 끊기면...
    public void onClose(int code, String reason, boolean remote) {
        Log.d(TAG, "onClose called");
    }

    //에러가 발생하면..
    public void onError(Exception ex) {
        Log.d(TAG, "onError called");
    }
}
