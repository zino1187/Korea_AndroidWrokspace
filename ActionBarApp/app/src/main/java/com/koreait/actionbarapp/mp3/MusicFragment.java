package com.koreait.actionbarapp.mp3;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.koreait.actionbarapp.MainActivity;
import com.koreait.actionbarapp.R;

public class MusicFragment extends Fragment {
    ListView listView;
    MusicAdapter musicAdapter;
    MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //프레그먼트가 사용할 디자인 인플레이션!!!
        View view = inflater.inflate(R.layout.fragment_music, container, false);
        listView = (ListView)view.findViewById(R.id.listView);
        musicAdapter = new MusicAdapter((MainActivity) this.getContext(), this);
        listView.setAdapter(musicAdapter); //리스트뷰와  어댑터 연결

        //뮤직플레이어 생성
        return view;//인플레이션 결과 반환!!!(우리의 경우 ConstraintLayout 임)

    }

    public void playMusic(int resId){
        mediaPlayer = MediaPlayer.create(this.getContext(), resId);
        mediaPlayer.setLooping(false); //무한루프 재생 금지
        mediaPlayer.start();
    }
    public void stopMusic(int resId){
        mediaPlayer.stop();
    }
}
