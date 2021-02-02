package com.koreait.graphicapp.gallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


//URL이미지를 보여줄 뷰
public class GalleryView extends View {
    int index; //배열 접근용 인덱스 0,1,2...

    String[] imgUrl={
            "http://image.dongascience.com/Photo/2020/03/5bddba7b6574b95d37b6079c199d7101.jpg"
            ,"https://image-notepet.akamaized.net/resize/620x-/seimage/20190222%2F88df4645d7d2a4d2ed42628d30cd83d0.jpg"
            ,"http://image.dongascience.com/Photo/2017/03/14900752352661.jpg"
            ,"https://cdn.imweb.me/upload/S201807025b39d1981b0b0/5cac274d00b12.jpg"
            ,"http://www.petzzi.com/data/editor/1601/405ec2eaaa3b9787ab2dcbdf7e7edf5e_1453873939_0913.jpg"
            ,"https://image.ytn.co.kr/general/jpg/2019/0514/201905141450310305_d.jpg"
            ,"https://i.pinimg.com/originals/6c/63/86/6c638603fb2bfa90bf0f54641491b855.jpg"
            ,"https://i.pinimg.com/564x/fb/ae/df/fbaedf3e5349215bd7576049b54b42ff.jpg"
            ,"https://i.pinimg.com/originals/d0/35/d2/d035d2f3ed9da59e3f0569242d8cf401.jpg"
            ,"https://i.pinimg.com/originals/8c/47/65/8c4765ac7a0e7029c3ede3178198e81e.jpg"
    };
    Bitmap bitmap;

    public GalleryView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        loadImage(imgUrl[index]);
  }

    public void loadImage(String path){
        //네트워크 작업이므로, 쓰레드 필요
        Thread thread = new Thread(){
            public void run() {
                try {
                    URL url = new URL(path);
                    bitmap = BitmapFactory.decodeStream(url.openStream());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, 0, 0, null);
    }
}






