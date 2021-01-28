package com.koreait.actionbarapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //현재 액티비티에 메뉴를 추가하려면 즉 액션바를 추가하려면 인플레이션 시키는 메서드 재정의

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //메뉴전용 인플레이터가 필요하다
        MenuInflater menuInflater=this.getMenuInflater();
        menuInflater.inflate(R.menu.navi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String msg=null;

        switch (item.getItemId()){
            case android.R.id.home:msg="Home";break;
            case R.id.mp3:msg="mp3";break;
            case R.id.chat:msg="chat";break;
            case R.id.gallery:msg="gallery";break;
            case R.id.settings:msg="settings";break;
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        return true;
    }
}

















