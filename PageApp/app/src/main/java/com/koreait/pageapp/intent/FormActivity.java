package com.koreait.pageapp.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.koreait.pageapp.R;

public class FormActivity extends AppCompatActivity implements View.OnClickListener {
    String TAG=this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        Button bt = (Button)findViewById(R.id.bt_send);
        bt.setOnClickListener(this);//위젯 버튼과 리스너와의 연결
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "나 눌렀어?");
        send();
    }

    //다른 액티비티를 호출하자!!
    public void send(){
        //아래와 같이 대상 클래스를 정확히 명시하는 인텐트 사용법을 가리켜 명시적(explicit) 인텐트라 한다
        Intent intent = new Intent(this, ReceiveActivity.class);
        startActivity(intent);
    }
}