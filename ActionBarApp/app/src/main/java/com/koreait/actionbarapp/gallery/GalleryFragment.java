package com.koreait.actionbarapp.gallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.koreait.actionbarapp.MainActivity;
import com.koreait.actionbarapp.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GalleryFragment extends androidx.fragment.app.Fragment {
    GridView gridView;
    GalleryAdapter galleryAdapter;
    Button bt_load;
    ArrayList<Gallery> galleryList;
    Handler handler;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_gallery, container, false);
        gridView =view.findViewById(R.id.gridView);
        bt_load =view.findViewById(R.id.bt_load);
        galleryAdapter = new GalleryAdapter((MainActivity) this.getContext());

        gridView.setAdapter(galleryAdapter);//그리드뷰와 어댑터와 연결
        galleryList = new ArrayList<Gallery>();

        handler = new Handler(Looper.getMainLooper()){
            public void handleMessage(@NonNull Message message) {
                galleryAdapter.notifyDataSetChanged(); //어댑터 다시 동작
                gridView.invalidate();//UI갱신
            }
        };

        //버튼과 리스너 연결
        bt_load.setOnClickListener(e->{
            load("http://172.30.1.28:7777/images/1781.jpg");
        });

        return view;
    }

    //네트워크상 웹서버에 접속하여 이미지를 가져오자!!!
    public void load(String image){
        Thread thread = new Thread(){
            public void run() {
                try {
                    URL url = new URL(image);
                    InputStream is = url.openStream(); //지정한 URL자원에 대한 스트림을 취득!!
                    Bitmap bitmap = BitmapFactory.decodeStream(is);//비트맵 객체 취득!!
                    //취득한 이미지 정보를 어댑터가 사용중인 데이터에 대입!! (계획...)
                    Gallery gallery =new Gallery();
                    gallery.setBitmap(bitmap);
                    galleryList.add(gallery); //리스트에 추가!!!
                    galleryAdapter.galleryList = galleryList;//어댑터의 데이터에 대입

                    // 개발자가 정의한 쓰레드는 디자인을 제어할 수 없으므로, 핸들러에게 부탁하자!!
                    handler.sendEmptyMessage(0);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start(); //쓰레드로 네트워크 출발~~
    }

}
