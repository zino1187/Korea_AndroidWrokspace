package com.study.app0121;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list); //xml 에서 명시된 객체들의 인스턴스가 메모리에 올라오게 된다
                                                        //이 과정을 전문용어로 inflation이라 한다~~
        GridView listView = (GridView)this.findViewById(R.id.listView);  //document.getElementById()와 비슷함..어렵다고 하지말것...

        /*리스트뷰에 보여질 모델을 구성해보자~!!*/
        String[] fruit={"딸기","사과","배","멜론","바나나","포도","복숭아","키위","레몬"};

        //java swing에서 jtable이라는 뷰와 실제 데이터를 연결해주는 객체인 테이블모델이 존재하듯, 안드로이드에서도
        // ListView와 데이터를 연결해주는 객체가 지원되는데, 이 객체를 가리켜 중간자라 해서 어댑터라 한다..
        //ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1, fruit);

        //우리가 원하는 레이아웃이 아닌 경우 직접 만들면 된다!!!
        //ArrayAdapter 말 그래도 하나의 배열을 이용하여 단일 아이템만 표현할 수 있는 단수한 어댑터이다..
        //복합적인 뷰를 표현할때는 어떻게 해야 하는가? Swing 개발할때처럼 Model클래스를 재정의하듯이, Adapter클래스를 재정의해야함..
        //내일..
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.fruit_item, fruit);
        //listView.setAdapter(adapter);

        //MyButton myButton = new MyButton(this);
        //myButton.setText("나버튼");


    }
}