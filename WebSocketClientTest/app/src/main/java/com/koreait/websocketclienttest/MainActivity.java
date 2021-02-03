package com.koreait.websocketclienttest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;




public class MainActivity extends AppCompatActivity {
    String TAG=this.getClass().getName();
    private WebSocketClient webSocketClient;

    private void createWebSocketClient() {
        try {
            // cc = new ChatClient(new URI(uriField.getText()), area, ( Draft ) draft.getSelectedItem() );
            webSocketClient = new WebSocketClient(new URI("ws://172.30.1.8:9999")) {
                @Override
                public void onMessage(String message) {
                    Log.d(TAG, "got: " + message + "\n");
                }



                @Override
                public void onOpen(ServerHandshake handshake) {
                    Log.d(TAG,"You are connected to ChatServer: " + getURI() + "\n");
                    send("hi im android");
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {

                }

                @Override
                public void onError(Exception ex) {

                }
            };
            webSocketClient.connect();
        } catch (URISyntaxException ex) {
            Log.d(TAG, " is not a valid WebSocket URI\n");
        }

    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createWebSocketClient();
    }

}