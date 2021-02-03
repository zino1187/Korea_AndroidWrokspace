package com.koreait.websocketclient;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

public class DetailDialog extends Dialog {
    Button bt_edit, bt_del;
    EditText t_title, t_writer, t_content;

    public DetailDialog(@NonNull Context context) {
        super(context);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //다이얼로그 창의 크기 지정을 시도해본다!!
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();


        //디자인된  xml을 인플레이션
        setContentView(R.layout.dialog_detail); //xml
        bt_edit = findViewById(R.id.bt_edit);
        bt_del = findViewById(R.id.bt_del);
        t_title = findViewById(R.id.t_title);
        t_writer = findViewById(R.id.t_writer);
        t_content = findViewById(R.id.t_content);

        bt_edit.setOnClickListener(e->{
            edit();
        });

        bt_del.setOnClickListener(e->{
            del();
        });
    }

    public void setData(Board board){
        t_title.setText(board.getTitle());
        t_writer.setText(board.getWriter());
        t_content.setText(board.getContent());
    }

    public void edit(){
    }

    public void del(){
    }
}
