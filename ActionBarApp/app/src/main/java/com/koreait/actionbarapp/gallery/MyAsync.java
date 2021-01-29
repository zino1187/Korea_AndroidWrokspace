package com.koreait.actionbarapp.gallery;

import android.os.AsyncTask;

//제이쿼리의 JQuery ajax 처럼, 안드로이드에서도 웹서버와의 통신시
//자주 사용되는 비동기 객체가 지원된다...but.  2019말에 Deprecated..
//AsyncTask<비동기로 작업할때 사용될 파라미터 주로 url , 진행상황 데이터  , 응답데이터(웹서버응답데이터)>
public class MyAsync extends AsyncTask<String, Void, String> {
    //비동기 요청 전에 하고싶은 작업...(메인 쓰레드로 수행 : UI 제어가능)
    protected void onPreExecute() {
        super.onPreExecute();
    }
    //비동기 요청 (쓰레드로 수행)
    protected String doInBackground(String... strings) {
        return null;
    }
    //요청하는 중에 처리할 작업...(메인 쓰레드로 수행: UI 제어가능)
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
    //요청 후 받은 응답데이터 처리(메인 쓰레드로 수행: UI 제어가능)
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
