package com.koreait.pageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
//서블릿 : 우리가 new? --> 시스템에 관리를 맡긴다.. --> 생명주기
//생명주기를 메서드를 제공하여, 개발자로 하여금 제어가 가능하도록...
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}