package com.koreait.websocketclient;

import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;
import org.java_websocket.drafts.Draft;

public class MyWebSocketClient extends WebSocketClient {
    String TAG=this.getClass().getName();

    public MyWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    //서버와 연결되면...
    public void onOpen(ServerHandshake handshakedata) {
        Log.d(TAG, "onOpen called");
        this.send("눈물이 앞을 가린다");
    }

    //메시지가 도착하면..
    public void onMessage(String message) {
        Log.d(TAG, "onMessage called");
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
