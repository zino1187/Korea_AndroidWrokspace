package com.koreait.graphicapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View {
    String TAG=this.getClass().getName();
    Paint paint ;
    int x=300,y=300;
    Thread gameThread;//메인쓰레드를 무한루프에 빠지게 해서는 안되므로, 별도의 쓰레드사용
    boolean gameFlag=true; //쓰레드를 실행할지 여부를 결정

    //자바에서 인스턴스 생성할 거면, 아래의 생성자만 두면 되고
    public MyView(Context context) {
        super(context);
        Log.d(TAG, "MyView(Context context) 생성자 호출");
    }

    //xml에서 사용할 거면, xml의 속성까지 넘겨받아야하므로, AttributeSet이 잇는 생성자
    //도 정의하자!!
    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "MyView(Context context, AttributeSet attrs) 생성자 호출");
        Log.d(TAG, "attrs is : "+attrs);
        paint = new Paint(Color.RED);
    }
    //자바스탠다드처럼, 모든 컴포넌트는 스스로를 그린다..
    //따라서 개발자가 그래픽처리를 주도하려면, onDraw() 메서드를 재정의하면 된다!!

    //주의 javaSE에서는 팔레트가 Graphics가 객체이지만,안드로이드에선 Canvas 이다
    //즉 도화지가 아니다!!!!!
    protected void onDraw(Canvas canvas) {
        //사각형 객체 생성
        Rect rect=new Rect(x,y,x+100,y+100);
        canvas.drawRect(rect, paint);
    }

    //무한루프
    public void gameLoop(){
        while(gameFlag) {
            x++;
            y++;
            this.invalidate();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}







