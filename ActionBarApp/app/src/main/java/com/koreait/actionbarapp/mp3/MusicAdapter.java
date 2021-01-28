package com.koreait.actionbarapp.mp3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.koreait.actionbarapp.MainActivity;
import com.koreait.actionbarapp.R;

/*ListView에게 데이터 정보를 제공할 어댑터 정의*/
public class MusicAdapter extends BaseAdapter {
    int[] files= {R.raw.music1, R.raw.music2, R.raw.music3};
    LayoutInflater layoutInflater;
    MainActivity mainActivity;

    public MusicAdapter(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        layoutInflater = mainActivity.getLayoutInflater();
    }

    //몇건?
    public int getCount() {
        return files.length;
    }

    //지정한 위치의 데이터 반환
    public Object getItem(int position) {
        return files[position];
    }

    //지정한 위치의 아이디 반환(식별값:개발자가 결정)
    public long getItemId(int position) {
        return 0; //pk
    }

    //지정한 위치에 들어갈 뷰 반환
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=null;
        if(convertView==null){//최초로 등장하는 아이템
            view = layoutInflater.inflate(R.layout.item_music, parent, false);
        }else{
            view = convertView;
        }
        return view;
    }
}
