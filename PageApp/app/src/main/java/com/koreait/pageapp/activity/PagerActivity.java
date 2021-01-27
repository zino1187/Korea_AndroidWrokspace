package com.koreait.pageapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.koreait.pageapp.R;

public class PagerActivity extends AppCompatActivity {
    ViewPager viewPager;
    MyViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        viewPager =(ViewPager)findViewById(R.id.viewPager);

        //뷰페이저와 어댑터와의 연결!!!
        adapter = new MyViewPagerAdapter(this.getSupportFragmentManager(), 0);
        viewPager.setAdapter(adapter);
    }
}