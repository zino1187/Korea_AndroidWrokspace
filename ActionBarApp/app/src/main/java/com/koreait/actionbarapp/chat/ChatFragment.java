package com.koreait.actionbarapp.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.koreait.actionbarapp.R;

import java.io.IOException;
import java.net.Socket;

public class ChatFragment extends Fragment {
    Socket socket;
    Thread thread; //네트워크 접속을 위한 쓰레드(메인쓰레드는 접속,대기,루프등에 빠지게 해서는 안됨)
    EditText t_ip, t_port;
    TextView t_log;
    EditText t_input;
    ChatThread chatThread;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        t_ip = (EditText)view.findViewById(R.id.t_ip);
        t_port = (EditText)view.findViewById(R.id.t_port);
        t_log = (TextView)view.findViewById(R.id.t_log);
        t_input = (EditText)view.findViewById(R.id.t_input);

        //버튼을 얻어와 이벤트 연결
        Button bt_connect = (Button)view.findViewById(R.id.bt_connect);
        bt_connect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                connectServer();
            }
        });
        return view;//인플레이션 시킨 결과 뷰 반환(우리의 경우 RelativeLayout)
    }

    //채팅 서버에 접속!!
    public void connectServer(){
        String ip=t_ip.getText().toString();
        int port=Integer.parseInt(t_port.getText().toString());

        thread = new Thread(){
            public void run() {
                try {
                    socket = new Socket(ip, port); //네트워크 접속 시도하러 출발!!!
                    chatThread = new ChatThread(socket);
                    chatThread.start();//청취 시작!!!!
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    //메시지 보내기!!
    public void send(View view){

    }
}
