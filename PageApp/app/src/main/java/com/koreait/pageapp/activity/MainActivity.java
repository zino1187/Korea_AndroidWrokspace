package com.koreait.pageapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.koreait.pageapp.R;

//서블릿 : 우리가 new? --> 시스템에 관리를 맡긴다.. --> 생명주기
//생명주기를 메서드를 제공하여, 개발자로 하여금 제어가 가능하도록...
public class MainActivity extends AppCompatActivity {
    String TAG=this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void showPage(View view){
        Log.d(TAG, "당신이 클릭한 버튼은 "+view.getId());

        String msg=null;
        Intent intent=null;
        switch (view.getId()){
            case R.id.bt_red:
                msg="Red";

                break;
            case R.id.bt_blue:msg="Blue";

                break;
            case R.id.bt_yellow:msg="Yellow";

                break;
        }
        this.startActivity(intent);
        Log.d(TAG, msg);
    }

}