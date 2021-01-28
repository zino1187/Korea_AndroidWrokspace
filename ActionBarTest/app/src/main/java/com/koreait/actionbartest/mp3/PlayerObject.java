package com.koreait.actionbartest.mp3;

import android.content.Context;
import android.media.MediaParser;
import android.media.MediaPlayer;

import com.koreait.actionbartest.R;

public class PlayerObject {
    Context context;
    MediaPlayer mediaPlayer;

    public void playMusic(Context context){
        try {
            mediaPlayer = MediaPlayer.create(context, R.raw.preview);
            mediaPlayer.setLooping(false);
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
