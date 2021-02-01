package com.koreait.diaryapp.animation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.koreait.diaryapp.R;

public class AnimationActivity extends AppCompatActivity {

    ViewPager viewPager;
    MyPagerAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        viewPager = findViewById(R.id.viewPager);
        adapter = new MyPagerAdapter(getSupportFragmentManager(),0);
        viewPager.setAdapter(adapter);//뷰페이저와 어댑터 연결
    }
}