package com.koreait.actionbarapp.mp3;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.koreait.actionbarapp.R;

/*ListView에게 데이터 정보를 제공할 어댑터 정의*/
public class MusicAdapter extends BaseAdapter {
    int[] files= {R.raw.music1, R.raw.music2, R.raw.music3};

    //몇건?
    public int getCount() {
        return 0;
    }

    //지정한 위치의 데이터 반환
    public Object getItem(int position) {
        return null;
    }

    //지정한 위치의 아이디 반환(식별값:개발자가 결정)
    public long getItemId(int position) {
        return 0;
    }

    //지정한 위치에 들어갈 뷰 반환
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
