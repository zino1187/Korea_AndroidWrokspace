package com.koreait.boardclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    BoardAdapter boardAdapter;
    HttpManager httpManager;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //리스트와 어댑터 연결!!
        //디자인이 복잡한(복합뷰) ListView는 BaseAdapter 를 재정의해야 한다..
        listView = this.findViewById(R.id.listView); //JTable
        boardAdapter = new BoardAdapter(this);
        listView.setAdapter(boardAdapter);//리스트뷰와 어댑터와의 연결!!!
        httpManager = new HttpManager();

        handler = new Handler(Looper.getMainLooper()){

            //handleMessage 영역은 UI 를 제어할 수 있는 영역
            public void handleMessage(@NonNull Message msg) {
                Bundle bundle = msg.getData();
                ArrayList<Board> boardList=bundle.getParcelableArrayList("boardList");
                boardAdapter.list = boardList;//어댑터에 리스트 주입
                boardAdapter.notifyDataSetChanged();
            }
        };
    }
    public void regist(View view){

    }

    public void getList(View view){
        //네트워크 통신을 위한 쓰레드 생성 및 실행
        Thread thread = new Thread(){
            public void run() {
                ArrayList<Board> boardList = httpManager.requestByGet("http://172.30.1.28:8888/rest/board");

                //핸들러에 요청 시점
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("boardList", boardList);
                message.setData(bundle);

                handler.sendMessage(message); //UI에 대신 뭐좀 해달라고 부탁!!
            }
        };
        thread.start();
    }
}










