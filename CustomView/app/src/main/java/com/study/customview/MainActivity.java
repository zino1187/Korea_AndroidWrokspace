package com.study.customview;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout content=(LinearLayout) this.findViewById(R.id.content);
        LayoutInflater layoutInflater=this.getLayoutInflater();

        for(int i=0;i<100;i++) {
            layoutInflater.inflate(R.layout.product_item, content);
        }

        for(int i=0;i<content.getChildCount();i++) { //LinearLayout의 갯수
            Member member = new Member();
            member.setTitle("Stone"+i);
            member.setBrand("Brand"+i);
            member.setPrice(i*1000+"");
            LinearLayout container =(LinearLayout) content.getChildAt(i);
            container =(LinearLayout)container.getChildAt(1);

            TextView title = (TextView)container.getChildAt(0);
            TextView brand = (TextView)container.getChildAt(1);
            TextView price = (TextView)container.getChildAt(2);

            title.setText(member.getTitle());
            brand.setText(member.getBrand());
            price.setText(member.getPrice());
        }
    }

    public void loadData(View view){
        //통신 쓰레드를 동작시키자!!
        ConnectManager manager = new ConnectManager("http://172.30.1.56:8888/rest/member", null);
        manager.start();
    }
}