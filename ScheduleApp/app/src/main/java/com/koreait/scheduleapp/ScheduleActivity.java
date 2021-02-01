package com.koreait.scheduleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ScheduleActivity extends AppCompatActivity {
    LayoutInflater layoutInflater;
    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        //view 받아놓기
        tableLayout =this.findViewById(R.id.tableLayout);

        //back 버튼 추가
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        layoutInflater = this.getLayoutInflater();

        createCell();//테이블 만들기!!
    }

    //back 버튼에 이벤트 연결
    @Override
    public boolean onSupportNavigateUp() {
        this.finish();//현재 액티비티는 스택에서 제거!!
        return true;
    }

    //달력의 틀 만들기
    public void createCell(){
        for(int a=0;a<7;a++){ //7층짜리 테이블의 row
            //true: 지정한 부모뷰에 부착하여, 그 부모뷰를 반환
            //false : 지정한 부모뷰에 부착하지 않으므로, xml의 최상위 뷰를 반환
            TableRow tableRow = (TableRow)layoutInflater.inflate(R.layout.item_row, tableLayout, false);

            //7개의 셀(날짜 박스)만들기
            for(int i=0;i<7;i++){
                //row에 셀을 부착
                ViewGroup  cell=(ViewGroup) layoutInflater.inflate(R.layout.item_cell, tableRow, false);

                //셀에 들어갈 텍스트뷰 생성 및 부착하기
                TextView t_title=(TextView)layoutInflater.inflate(R.layout.item_text, cell, false);
                cell.addView(t_title); //td안의 컨텐츠 부착하는 느낌..
                tableRow.addView(cell); //tr에 td부착하는 느낌...
            }
            tableLayout.addView(tableRow);//각층을 테이블에 부착!!
        }
    }
}











