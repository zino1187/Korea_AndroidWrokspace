package com.study.app0122;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String TAG=this.getClass().getName();
    ViewGroup wrapper;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//이 메서드 자체가 이미 inflation 기능이 들어있다!!
        wrapper=(ViewGroup)this.findViewById(R.id.wrapper);

        handler = new Handler(){
            public void handleMessage(@NonNull Message msg) {
                //여기서 메인쓰레드로 할 수 있는 작업을 요청하면 된다..
                Bundle bundle = msg.getData();

                printData(bundle.getString("data"));
            }
        };
    }

    //게시물 출력하기(서버로 부터 받은 json String 데이터를 넘겨받아 출력)
    public void printData(String data){
        Log.d(TAG, "핸들러로부터 전달받은 데이터는 "+data);

        //이시점 부터는 String을 객체화시켜서 사용해야 한다!!
        List<Member> memberList = new ArrayList<Member>();

        try {
            JSONArray jsonArray = new JSONArray(data);
            Log.d(TAG, "제이슨 배열의 길이는 "+jsonArray.length());

            for(int i=0;i<jsonArray.length();i++){
                JSONObject json=(JSONObject) jsonArray.get(i);
                //Member VO에 대입
                Member member = new Member();
                member.setM_id((String)json.get("m_id"));
                member.setM_pass((String)json.get("m_pass"));
                member.setM_name((String)json.get("m_name"));
                memberList.add(member);//리스트에 멤버추가!!
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //재사용하기 위해 미리 정의해놓은 레이아웃을 파일을 인플레이션 시켜본다
        //inflation 이란? xml에서 정의해놓은 태그들을 실제 안드로이드 객체로 인스턴스화 시키는 과정
        LayoutInflater layoutInflater=this.getLayoutInflater();

        for(int i=0;i<memberList.size();i++) {
            Member member = memberList.get(i);

            //아래의 인플레이션이 발생한 후, profile_item.xml의 최상위 레이아웃이 반환된다..
            ViewGroup root_wrapper =(ViewGroup) layoutInflater.inflate(R.layout.profile_item, wrapper);
            Log.d(TAG, "인플레이션 결과 뷰그룹은 "+root_wrapper);

            Log.d(TAG, "root_wrapper의 자식 수는 "+root_wrapper.getChildCount());

            //profile의 루트인 LinearLayout 에 접근
            ViewGroup profile_root =(ViewGroup) root_wrapper.getChildAt(i);
            Log.d(TAG, "profile_root 리니어는 "+profile_root);

            ViewGroup text_root=(ViewGroup) profile_root.getChildAt(1);
            Log.d(TAG, "text_root 리니어는 "+text_root);

            TextView t_id=(TextView)text_root.getChildAt(0);//아이디 텍스트뷰
            TextView t_pass=(TextView)text_root.getChildAt(1);//비밀번호 텍스트뷰
            TextView t_name=(TextView)text_root.getChildAt(2);//이름 텍스트뷰

            t_id.setText(member.getM_id());
            t_pass.setText(member.getM_pass());
            t_name.setText(member.getM_name());
        }
    }
    public void loadData(View view){
        Log.d(TAG, "A");

        //쓰레드는 하나의 프로세스내에서 독립적으로(또한 비동기적 특징도 있음.) 실행되는 , 또하나의 세부 실행단위
        ConnectManager manager = new ConnectManager(this,"http://172.30.1.56:8888/rest/member",null);
        manager.start();
    }
}

