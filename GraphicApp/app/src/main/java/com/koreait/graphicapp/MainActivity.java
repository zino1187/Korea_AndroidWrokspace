package com.koreait.graphicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    MyView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myView = findViewById(R.id.myView);
    }

    //뷰에 들어있는 사각형 움직이기!!
    public void move(View view){
        //myView.x+=10;
        //myView.y+=10;
        //자바의 repaint()  --> 뷰.invalidate()
        //myView.invalidate(); // 해당 뷰의 onDraw()메서드를 호출!!
        myView.gameFlag=true;
        Thread gameThread = new Thread(){
            public void run() {
                myView.gameLoop();
            }
        };
        gameThread.start();
    }

    public void stop(View view){
        myView.gameFlag=false;

    }
}









