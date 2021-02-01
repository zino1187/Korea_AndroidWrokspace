package com.koreait.diaryapp.animation;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class MyPagerAdapter extends FragmentStatePagerAdapter {
    Fragment[] fragments = new Fragment[5];

    public MyPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        fragments[0] = new AlphaFragment(); //투명
        fragments[1] = new ScaleFragment(); //크기
        fragments[2] = new TranslateFragment();//이동
        fragments[3] = new RotateFragment();//회전
        fragments[4] = new SetFragment();//복합
    }

    public Fragment getItem(int position) {
        return fragments[position];
    }

    public int getCount() {
        return fragments.length;
    }

}
