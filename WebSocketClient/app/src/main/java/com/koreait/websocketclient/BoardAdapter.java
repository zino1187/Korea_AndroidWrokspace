package com.koreait.websocketclient;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

//ListView에 제공할 정보를 구성해보자!!
public class BoardAdapter extends BaseAdapter {
    List<Board> boardList = new ArrayList<Board>();
    LayoutInflater layoutInflater;
    MainActivity mainActivity;

    public BoardAdapter(MainActivity mainActivity){
        this.mainActivity=mainActivity;
        layoutInflater = mainActivity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return boardList.size();
    }

    @Override
    public Object getItem(int position) {
        return boardList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return boardList.get(position).getBoard_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if(convertView !=null){ //이미 뷰가 존재한다면...기존꺼 사용
            view = convertView;
        }else{//아니라면, 새롭게 인플레이션..
            view = layoutInflater.inflate(R.layout.item_board, parent, false);//인플레이션시킬거임..(새롭게 생성할 것임)
        }
        //뷰에 데이터 채우기!!
        TextView t_title = view.findViewById(R.id.t_title);
        TextView t_writer = view.findViewById(R.id.t_writer);
        TextView t_regdate = view.findViewById(R.id.t_regdate);
        TextView t_hit = view.findViewById(R.id.t_hit);

        Board board = boardList.get(position);

        t_title.setText(board.getTitle());
        t_writer.setText(board.getWriter());
        t_regdate.setText(board.getRegdate());
        t_hit.setText(Integer.toString(board.getHit()));

        return view;
    }
}
