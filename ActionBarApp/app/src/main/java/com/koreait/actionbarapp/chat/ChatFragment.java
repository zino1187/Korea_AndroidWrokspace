package com.koreait.actionbarapp.chat;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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
    Button bt_send;
    ChatThread chatThread;
    Handler handler;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        t_ip = (EditText)view.findViewById(R.id.t_ip);
        t_port = (EditText)view.findViewById(R.id.t_port);
        t_log = (TextView)view.findViewById(R.id.t_log);
        t_input = (EditText)view.findViewById(R.id.t_input);
        bt_send = (Button)view.findViewById(R.id.bt_send);

        //버튼을 얻어와 이벤트 연결
        Button bt_connect = (Button)view.findViewById(R.id.bt_connect);
        bt_connect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                connectServer();
            }
        });
        bt_send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                send();
            }
        });
        handler= new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message message) {
                //쓰레드가 부탁한 UI제어를 여기서 대신 해준다!!
                Bundle bundle = message.getData();
                String msg=bundle.getString("msg");
                t_log.append(msg+"\n");
            }
        };

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
                    chatThread = new ChatThread(socket, ChatFragment.this);
                    chatThread.start();//청취 시작!!!!
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    //메시지 보내기!!
    public void send(){
        Thread thread = new Thread(){
            public void run() {
                chatThread.send(t_input.getText().toString());
                //핸들러에게 부탁하여 로그 남기기
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("msg", t_input.getText().toString());
                message.setData(bundle);
                handler.sendMessage(message);
            }
        };
        thread.start();
    }
}
