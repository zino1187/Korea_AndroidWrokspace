package com.koreait.pageapp;


import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RedActivity extends AppCompatActivity {
    //생명주기 메서드가 있다..

    //액티비티의 인스턴스 생성직후 초기활을 위한 메서드 (서블릿의 init과 목적이 동일)
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        //여기서 보여질 뷰 선택 등...
        setContentView(R.layout.red);//xml에 명시한 객체들을 인스턴스화 시킴!!(inflation)
    }
}










