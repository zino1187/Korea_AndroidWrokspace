package com.koreait.graphictest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class FrameAnimationView extends View {
    Thread thread;
    Bitmap bitmap;
    URL url;
    public FrameAnimationView(Context context) {
        super(context);
        thread = new Thread(){
            public void run() {
                loadImage();
            }
        };
        thread.start();
    }

    public void loadImage(){
        try {
            url = new URL("https://www.guidedogs.org/wp-content/uploads/2019/11/website-donate-mobile.jpg");
            bitmap = BitmapFactory.decodeStream(url.openStream());
            this.invalidate();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(bitmap !=null){
            canvas.drawBitmap(bitmap,0,0, null);

        }
    }
}
