package com.koreait.websokcettest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {
    private Socket mSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 설명의 편의를 위해 onCreate()메서드에 추가하였으나,꼭 onCreate() 메서드에 위치할 필요는 없을 것 같습니다.
        try {
            mSocket = IO.socket("http://192.168.35.96:7979");
            mSocket.on(Socket.EVENT_CONNECT, onConnect); //접속 이벤트가 감지되면
            mSocket.on("tick", onMessageReceived); //서버가 보낸 메시지가 감지되면
            mSocket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    // Socket서버에 connect 된 후, 서버로부터 전달받은 'Socket.EVENT_CONNECT' Event 처리.
    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            System.out.println("서버로 부터 접속했어요");
        }
    };

    // 서버로부터 전달받은 'chat-message' Event 처리.
    private Emitter.Listener onMessageReceived = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            // 전달받은 데이터는 아래와 같이 추출할 수 있습니다.
            JSONObject receivedData = (JSONObject) args[0];
            System.out.println("서버로 부터 메시지를 받았어요");
        }
    };

    public void send(){
        JSONObject data = new JSONObject();
        try {
            data.put("key1", "value1");
            data.put("key2", "value2");
            mSocket.emit("tok", data);
        } catch(JSONException e) {
            e.printStackTrace();
        }
    }
}