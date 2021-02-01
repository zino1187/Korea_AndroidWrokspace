package com.koreait.diaryapp;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

//데이터를 입력하기 위한 다이얼로그 창 정의
public class RegistDialog extends Dialog {
    EditText t_msg;
    Button bt_regist;

    public RegistDialog(@NonNull Context context) {
        super(context, android.R.style.Theme_DeviceDefault_Light_Dialog);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //다이얼로그 박스의 디자인 xml 인플레이션..
        setContentView(R.layout.dialog_regist);
        t_msg = findViewById(R.id.t_msg);
        bt_regist = findViewById(R.id.bt_regist);

        bt_regist.setOnClickListener(e->{
            System.out.println("등록할꺼야?");
            //insert 등록 작업후, 창 닫기
            this.dismiss();
        });
    }


}
