package com.koreait.diaryapp.animation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.koreait.diaryapp.R;

//투명도를 실습하기 위한 페이지
public class ScaleFragment extends Fragment {
    ImageView imgView;
    Button bt_start;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scale, container, false);
        imgView = view.findViewById(R.id.imgView);
        bt_start = view.findViewById(R.id.bt_start);

        bt_start.setOnClickListener(e->{
            showAni();
        });

        return view;
    }
    public void showAni(){
        Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.ani_scale );
        imgView.startAnimation(animation);
    }
}
