package com.koreait.actionbarapp.mp3;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koreait.actionbarapp.MainActivity;
import com.koreait.actionbarapp.R;

import java.util.ArrayList;
import java.util.List;

/*ListView에게 데이터 정보를 제공할 어댑터 정의*/
public class MusicAdapter extends BaseAdapter {
    String TAG=this.getClass().getName();

    List<Music> musicList=new ArrayList<Music>();
    LayoutInflater layoutInflater;
    MainActivity mainActivity;
    MusicFragment musicFragment;

    public MusicAdapter(MainActivity mainActivity, MusicFragment musicFragment){
        this.mainActivity = mainActivity;
        this.musicFragment=musicFragment;
        layoutInflater = mainActivity.getLayoutInflater();

        //리스트 구성하기
        int[] files={R.raw.music1, R.raw.music2, R.raw.music3};
        for(int i=0;i<files.length;i++) {
            Music music = new Music();
            music.setTitle("android music"+i);
            music.setFile(files[i]);
            musicList.add(music);
        }
    }

    //몇건?
    public int getCount() {
        return musicList.size();
    }

    //지정한 위치의 데이터 반환
    public Object getItem(int position) {
        return musicList.get(position);
    }

    //지정한 위치의 아이디 반환(식별값:개발자가 결정)
    public long getItemId(int position) {
        return 0; //pk
    }

    //지정한 위치에 들어갈 뷰 반환
    public View getView(int position, View convertView, ViewGroup parent) {

        Music music = musicList.get(position);
        View view=null;

        if(convertView==null){//최초로 등장하는 아이템
            view = layoutInflater.inflate(R.layout.item_music, parent, false);
        }else{
            view = convertView;
        }
        TextView t_title=view.findViewById(R.id.t_title);
        t_title.setText(music.getTitle());

        //버튼에 이벤트 연결하기!
        ImageView bt_play = (ImageView)view.findViewById(R.id.bt_play);
        ImageView bt_stop = (ImageView)view.findViewById(R.id.bt_stop);

        //자바스크립트에서는 클로저(closure) 자바는 Lambda
        bt_play.setOnClickListener(e->{
            Log.d(TAG, "파일명은 "+music.getTitle());
            musicFragment.playMusic(music.getFile());
        });

        bt_stop.setOnClickListener(e->{
            musicFragment.stopMusic(music.getFile());
        });
        return view;
    }
}



