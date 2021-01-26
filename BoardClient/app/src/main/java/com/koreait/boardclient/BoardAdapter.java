package com.koreait.boardclient;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

//ListView가 사용할 컨트롤러 클래스!! swing 에서의  TableModel과 그 역할이 같다
public class BoardAdapter extends BaseAdapter {
    MainActivity mainActivity;
    ArrayList<Board> list = new ArrayList<Board>();
    LayoutInflater layoutInflater; //XML로 안드로이드 객체를 인스턴스화 시키는 객체

    //swing TableModel이 제공하는 메서드와 동일하게 아래의 모든 메서드는
    //호출 주체가 ListView이다!!
    public BoardAdapter(MainActivity mainActivity){
        this.mainActivity=mainActivity;
        layoutInflater=this.mainActivity.getLayoutInflater();
    }

    @Override
    public int getCount() { //레코드 갯수가 몇개인지 반환..
        return list.size();
    }

    //ListView의 각 칸 아이템이 아닌, 배열 및 리스트의 각 요소값(디자인 X)
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    //ListView의 각 아이템 자리를 차지하는 뷰가 최초 및 새로 할당받았던 그 뷰..
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=null;

        if(convertView==null){ //발급받은 적이 없다면(null), 새로 발급해주자
            //아이템을 차지할 새로운 뷰를 생성해야 한다...
            //워낙 디자인이 복잡하다 보니, 순수 자바로 작성하지 말고, xml로부터 인플레이션 시키자
            //인플레이션 시킬때 주의점, 3번째 인수에 false 를 부여해야, 인풀레이션 결과의 최상단
            //컨테이너가 ListView가 아니게 반환됨..
            view=layoutInflater.inflate(R.layout.board_item, parent, false);
        }else{//발급받은 적이 있다면, 그대로 쓰자!!
            view = convertView;
        }

        //인플레이션에 의해 반환된 최상위 컨테이너인 view는 자신이 보유한 자식 요소에 접근할 수 있다.
        TextView tv_title = view.findViewById(R.id.item_title);
        TextView tv_writer = view.findViewById(R.id.item_writer);
        Board board = list.get(position);
        tv_title.setText(board.getTitle()+"(조회: "+board.getHit()+")");
        tv_writer.setText(board.getWriter()+"( "+board.getRegdate()+" )");

        return view;
    }
}
