package com.study.app0120;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.sql.DriverManager;

//안드로이드화면에 누가 보여져야 될지를 결정짓기 위해, 화면 활성화 객체인
//Activity를 지원하면, 하나의 화면당 1:1 대응된다.
//따라서 게시판을 안드로이드로 만들 경우 다음과 같이 구성하면 된다..
//목록, 글쓰기, 내용보기  총 3개의 액티비티가 필요하다!
public class MainActivity extends AppCompatActivity {
    private String TAG=this.getClass().getName();
    WebConnector web;

    //액티비티가 초기화될때 할 작업을 onCreate() 에서 처리해야 한다..
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView 화면을 구성할 컴포넌트를 생성 및 배치
        setContentView(R.layout.login_form);

    }

    //이벤트 메서드 정의
    public void regist(View view){
        //안드로이드도 Log4j를 도입했다!!
        //태그의 역할?? 로그에 출력되는 되는 메시지간 구분을 위한 태그..이 태그가 구분되어야 수많은 로그 중
        //개발자가 원하는 로그를 걸러낼 수 잇다..
        Log.d(TAG, "나 눌렀어?");

        //간접적으로 요청 시도
        web = new WebConnector();
        web.start();
    }
}