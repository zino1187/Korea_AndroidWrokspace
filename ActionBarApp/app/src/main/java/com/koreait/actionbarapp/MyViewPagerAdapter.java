package com.koreait.actionbarapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.koreait.actionbarapp.mp3.MusicFragment;

public class MyViewPagerAdapter extends FragmentStatePagerAdapter {
    Fragment[] fragments=new Fragment[1];

    public MyViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        fragments[0] = new MusicFragment(); //페이지 생성
    }

    //몇페이지?
    public int getCount() {
        return fragments.length; //페이지 수 반환
    }

    //각 포지션에 어떤 페이지를?
    public Fragment getItem(int position) {
        return fragments[position];
    }
}
