package com.koreait.graphictest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;

public class MyView extends View {
    String TAG=this.getClass().getName();
    Thread gameThread;
    boolean gameFlag=true;
    Paint paint;
    Hero hero = new Hero();

    public MyView(Context context) {
        super(context);
        paint = new Paint(Color.YELLOW);

        gameThread = new Thread(){
            public void run() {
                while(gameFlag){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    gameLoop();
                }
            }
        };
        gameThread.start();
    }

    public void gameLoop(){
        hero.tick();
        this.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw called...");
        canvas.drawRect(0,0,500,500,paint);
        hero.render(canvas);
    }
}





