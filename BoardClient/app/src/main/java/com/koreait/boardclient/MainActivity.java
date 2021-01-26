package com.koreait.boardclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    BoardAdapter boardAdapter;
    HttpManager httpManager;

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

    }
    public void regist(View view){

    }

    public void getList(View view){
        //네트워크 통신을 위한 쓰레드 생성 및 실행
        Thread thread = new Thread(){
            public void run() {
                httpManager.requestByGet("http://172.30.1.28:8888/rest/board");
            }
        };
        thread.start();
    }
}










