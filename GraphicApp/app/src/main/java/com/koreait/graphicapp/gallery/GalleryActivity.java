package com.koreait.graphicapp.gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.koreait.graphicapp.R;

public class GalleryActivity extends AppCompatActivity {
    GalleryView galleryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        galleryView = findViewById(R.id.galleryView);
    }
    public void prev(View view){}
    public void stop(View view){}

    public void auto(View view){
        Thread thread = new Thread(){
            public void run() {
                for(int i=0;i<galleryView.imgUrl.length-1;i++) {
                    //배열보다 작을때까지만.. 예)  9까지 존재할경우 8까지는 허용
                    galleryView.index++;
                    galleryView.loadImage(galleryView.imgUrl[galleryView.index]);
                    galleryView.invalidate();//갱신  onDraw() 재호출

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }

    public void next(View view){
        if(galleryView.index< galleryView.imgUrl.length-1) {
            //배열보다 작을때까지만.. 예)  9까지 존재할경우 8까지는 허용
            galleryView.index++;
            galleryView.loadImage(galleryView.imgUrl[galleryView.index]);
            galleryView.invalidate();//갱신  onDraw() 재호출
        }else{
            Toast.makeText(this,"더이상 이미지가 없습니다",Toast.LENGTH_SHORT).show();
        }
    }
}