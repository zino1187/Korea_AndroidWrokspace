package com.koreait.websocketclient;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

public class DetailDialog extends Dialog {

    public DetailDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //디자인된  xml을 인플레이션
        //setContentView(); //xml
    }
}
