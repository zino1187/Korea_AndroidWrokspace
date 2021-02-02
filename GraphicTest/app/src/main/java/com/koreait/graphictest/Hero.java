package com.koreait.graphictest;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Hero {
    int x,y;
    Paint paint;
    public Hero(){
        paint = new Paint();
        paint.setColor(Color.GREEN);
    }
    public void tick(){
        x+=1;
        y+=1;
    }
    public void render(Canvas canvas){
        RectF rect=new RectF(x,y,x+100, y+100);
        canvas.drawRect(rect, paint);
    }
}
