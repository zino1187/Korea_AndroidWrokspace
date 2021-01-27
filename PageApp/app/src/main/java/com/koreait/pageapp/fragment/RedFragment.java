package com.koreait.pageapp.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.koreait.pageapp.R;

//하나의 화면 일부를 담당하는 Fragment 는 , 일명 작은 액티비티라고도 불린다.
//따라서 액티비티에 생명주기 메서드가 있듯, Fragment 또한 생명주기 메서드가 지원된다..
public class RedFragment extends Fragment {
    String TAG=this.getClass().getName();
    //초기화 메서드

    //프레그먼트가 사용할 디자인 레이아웃 xml
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //매개변수로 넘겨받은 인플레이터를 이용하여  xml 인플레이션 하자!!
        //false로 지정해야 인플레이션된 xml의 최상위 뷰그룹이 반환된다..
        //true주면?? 인플레이션된 xml 의 최상위 뷰보다 더 바깥쪽 뷰가 반환되버림..
        Log.d(TAG, "onCreateView에 전달되는 container는 "+container);

        View view = inflater.inflate(R.layout.fragment_red, container, false);

        return view;
    }
}
