package com.koreait.actionbarapp.mp3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.koreait.actionbarapp.R;

public class MusicFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //프레그먼트가 사용할 디자인 인플레이션!!!
        View view = inflater.inflate(R.layout.fragment_music, container, false);
        return view;//인플레이션 결과 반환!!!(우리의 경우 ConstraintLayout 임)
    }
}
