package com.study.app0121;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class NetworkActivity extends AppCompatActivity {
    String TAG=this.getClass().getName(); //클래스명
    ConnectManager manager;
    EditText t_id;
    EditText t_pass;
    EditText t_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_network);
        //xml로부터 안드로이드의 객체들이 인스턴화된 시점이므로, 각 객체에
        //부여된 아이디를 이용하여 레퍼런스를 얻자!!
        t_id=this.findViewById(R.id.t_id);
        t_pass=this.findViewById(R.id.t_pass);
        t_name=this.findViewById(R.id.t_name);
    }
    //반드시 View를 매개변수로 넣어줘야 onClick 핸들러가 적용됨
    public void regist(View view){
        Log.d(TAG, "나 눌렀어?");

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"m_id\":\""+t_id.getText()+"\",");
        sb.append("\"m_pass\":\""+t_pass.getText()+"\",");
        sb.append("\"m_name\":\""+t_name.getText()+"\"");
        sb.append("}");

        manager = new ConnectManager("http://172.30.1.56:8888/rest/member", sb.toString());
        manager.start();
    }
}