package com.study.app01092;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String TAG=this.getClass().getName();
    AsyncManager asyncManager;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //inflation을 일으켜 객체들을 메모리에 올려놓음
        listView= (ListView)this.findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, FileListActivity.class);
                startActivity(intent);
            }
        });
    }

    public void getList(String data){
        List<String> list = new ArrayList<String>();

        try {
            JSONArray jsonArray = new JSONArray(data);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject obj = (JSONObject) jsonArray.get(i);
                list.add(obj.getString("name"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListAdapter listAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(listAdapter);
    }

    public void connect(View view){
        Log.d(TAG,"getList called");
        AsyncManager asyncManager = new AsyncManager(this);
        try {
            asyncManager.execute(new URL("http://192.168.35.96:8888/rest/cs/qna/list"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        /* 일반 쓰레드로 가져오기
        ConnectionThread thread = new ConnectionThread();
        thread.start();
        */

    }

}






