package com.koreait.pageapp.fragment;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

//ListView가 BaseAdapter에 의존하듯, ViewPager또한 PagerAdapter를 통해
//어떤 페이지가 보여질지를 구성한다!!
public class MyViewPagerAdapter extends FragmentStatePagerAdapter {
    Fragment[] fragments=new Fragment[3]; //총3페이지로 구성하겠다!!

    public MyViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);

        //페이지 생성
        fragments[0] = new RedFragment();
        fragments[1] = new BlueFragment();
        fragments[2] = new YellowFragment();
    }

    //총 페이지수
    public int getCount() {
        return fragments.length;
    }

    //각 페이지마다 보여질 프레그먼트..
    public Fragment getItem(int position) {
        return fragments[position];
    }
}
