package com.koreait.diaryapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_intro);
    }

    //메인 액티비티 호출
    public void goMain(View view){
        Intent intent = new Intent( this, MainActivity.class);
        startActivity(intent);//지정한 액티비티 호출!
    }
}













