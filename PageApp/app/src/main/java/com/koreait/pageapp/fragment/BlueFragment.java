package com.koreait.pageapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.koreait.pageapp.R;

//프레그먼트는 화면의 일부를 담당하는 작은 액티비티라 불린다..
//마치 html에서 화면의 일부 레이아웃을 div로 제어하듯.. 우리의 스크린을
//분할하여 처리하려면 Fragment를 사용할 수 있다..
//또한 자체적인 생명주기를 갖기 때문에, 이런 독립적면에서는 iframe과도 비슷...
public class BlueFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_blue, container, false);
        return view;//프레그먼트에 의해 보여질 뷰..
    }
}
