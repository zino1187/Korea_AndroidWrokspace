package com.koreait.actionbarapp.gallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.koreait.actionbarapp.MainActivity;
import com.koreait.actionbarapp.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GalleryAdapter extends BaseAdapter {
    String TAG=this.getClass().getName();
    MainActivity mainActivity;
    LayoutInflater layoutInflater;
    ArrayList<Gallery> galleryList = new ArrayList<Gallery>();

    public GalleryAdapter(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        layoutInflater=mainActivity.getLayoutInflater(); //액티비티를 통해 인플레이터 얻기
    }

    @Override
    public int getCount() {
        return galleryList.size(); //임시적으로...
    }

    @Override
    public Object getItem(int position) {
        return galleryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        Gallery gallery = galleryList.get(position);
        return gallery.getGallery_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=null; //이 메서드에서 최종 반환할 뷰

        if(convertView == null){//레이아웃 뷰가 존재하지 않는다면.. 인플레이션 시킴
            //false의 의미 지정한 parent에 부착하지 않고, 인플레이션 대상  xml의 최상위를 반환
            view = layoutInflater.inflate(R.layout.item_gallery, parent, false);
        }else{//이미 존재한다면, 기존 뷰 그래도 재사용함
            view=convertView;
        }
        
        //리스트에 들어있는 position 번째 Gallery 추출
        Gallery gallery  =galleryList.get(position);

        ImageView img = view.findViewById(R.id.img);
        TextView t_title = view.findViewById(R.id.t_title);

        img.setImageBitmap(gallery.getBitmap());
        t_title.setText(gallery.getTitle());

        img.setOnClickListener(e->{
            Toast.makeText(mainActivity, gallery.getGallery_id()+" 선택했어?", Toast.LENGTH_SHORT).show();
        });

        return view;
    }


}















