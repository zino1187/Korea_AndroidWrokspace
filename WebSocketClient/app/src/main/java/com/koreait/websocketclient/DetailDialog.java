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
    int board_id;
    MainActivity mainActivity;

    public DetailDialog(@NonNull Context context) {
        super(context);
        mainActivity =(MainActivity) context;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //다이얼로그 창의 크기 지정을 시도해본다!!
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        int width = (int)(this.getContext().getResources().getDisplayMetrics().widthPixels*0.90);
        int height = (int)(this.getContext().getResources().getDisplayMetrics().heightPixels*0.90);
        this.getWindow().setLayout(width, height);

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
        board_id = board.getBoard_id();//보관...얘는 수정대상이 아니므로..보관..
        t_title.setText(board.getTitle());
        t_writer.setText(board.getWriter());
        t_content.setText(board.getContent());
    }

    public void edit(){
        //유저가 수정한 데이터 VO로 담기!!
        Board board = new Board();
        board.setBoard_id(board_id);
        board.setTitle(t_title.getText().toString());
        board.setWriter(t_writer.getText().toString());
        board.setContent(t_content.getText().toString());

        Thread thread = new Thread(){
            public void run() {
                mainActivity.boardDAO.edit(board);
            }
        };
        thread.start();
    }

    public void del(){
        MainActivity mainActivity = (MainActivity)this.getContext();
        //mainActivity.boardDAO.del();
    }
}
