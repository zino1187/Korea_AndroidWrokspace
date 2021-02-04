package com.koreait.imageselector;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;

public class ContactActivity extends AppCompatActivity {
    String TAG=this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
    }

    public void openContact(View view){
        //주소록 앱은 우리의 앱과 다른 별도의 앱이므로, 샌드박스 정책에 의해 절대
        //내부로 접근이 불가능하다..따라서 주소록 데이터에 접근하기 위해서는
        //주소록 앱이 제공해주는 공개 방법에 의해서만 접근할 수 있는데, 이 공개된
        //접근방법을 제공해 주는 기술이 Content Provider이고, Content provider를
        //구현한 객체가 바로 ContentResolver이다...
        //컨텐트 프로바이더 얻기!!
        //매개변수 :  조회할 컬럼명, 조건절, 조건 파라미터, 정렬방향..
        Cursor cursor=this.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null,null,null);

        while(cursor.moveToNext()){
            Log.d(TAG, "전화번호는 = "+cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        }
    }
}