package com.koreait.actionbarapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    MyViewPagerAdapter myViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager)findViewById(R.id.viewPager);

        //ViewPager 는 디자인에 불과하기 때문에, 구성 데이터에 대한 정보를 어댑터를 통해 제공받는다!!
        myViewPagerAdapter = new MyViewPagerAdapter(this.getSupportFragmentManager(), 0);
        viewPager.setAdapter(myViewPagerAdapter);//뷰페이저와 어댑터 연결
    }

    //현재 액티비티에 메뉴를 추가하려면 즉 액션바를 추가하려면 인플레이션 시키는 메서드 재정의
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //메뉴전용 인플레이터가 필요하다
        MenuInflater menuInflater=this.getMenuInflater();
        menuInflater.inflate(R.menu.navi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String msg=null;

        switch (item.getItemId()){
            case android.R.id.home:msg="Home";break;
            case R.id.mp3:showPage(0);break;
            case R.id.chat:showPage(1);break;
            case R.id.gallery:showPage(2);break;
            case R.id.settings:showPage(3);break;
        }
        //Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        return true;
    }

    public void showPage(int position){
        viewPager.setCurrentItem(position, true);
    }
}

















