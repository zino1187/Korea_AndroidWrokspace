package com.study.app0121;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //화면구성에 사용할 뷰또는 레이아웃 지정
        //방법1) xml에 버튼을 배치하는 방법 setContentView(R.layout.custom);
        //방법2) xml을 이용하지 않고, 하드코딩으로 버튼을 생성하여 붙이는 방법
        /*
        Button bt = new Button(this);
        bt.setText("나버튼2");
        setContentView(bt);*/
        //방법3)  방법2를 응용한 방식으로, 기존버튼을 상속받아 재정의하는 방법
        //MyCustomButton bt = new MyCustomButton(this);
        //setContentView(bt);

        //방법4) 방법3에서 정의한 버튼을 xml에서 생성 및 배치하는 법
        //이때는 버튼의 생성자에 xml속성을 받을 수잇는 생성자를 오버라이딩 해놓아야 한다
        setContentView(R.layout.custom);

        //디비에서 불러온 갯수만큼 반복문....
        
    }
}