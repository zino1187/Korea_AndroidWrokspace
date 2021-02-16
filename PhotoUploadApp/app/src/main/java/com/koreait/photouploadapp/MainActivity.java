package com.koreait.photouploadapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    String TAG=this.getClass().getName();
    public static final int REQUEST_CAMERA=1;
    ImageView imageView;
    Bitmap bitmap; //현재 보고 잇는 사진에 대한 비트맵

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
    }
    //권한 승인 팝업으로부터 응답을 처리하자

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CAMERA){ //카메라 권한에 대한 요청이라면..
          if(grantResults[0] == PackageManager.PERMISSION_GRANTED){//승인을 했다면..
              callCamera();
          }else{ //거절했다면..
              AlertDialog.Builder alert = new AlertDialog.Builder(this);
              alert.setTitle("권한 안내").setMessage("서비스 이용을 위해서는 카메라권한을 승인하셔야 합니다").create().show();
          }
        }
    }

    @Override //다른 액티비티 호출 후 그 결과를 가져올때 호출됨..
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CAMERA){ //카메라에 대한 요청이었다면..
            //유저가 촬용한 사진 가져오기!!
            Bundle extras = data.getExtras();
            bitmap=(Bitmap)extras.get("data");

            //이미지뷰에 출력!
            imageView.setImageBitmap(bitmap);
        }
    }

    public void take(View view){
        //사진 촬영은 Dangerous권한이므로 권한 요청을 해야 한다
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.CAMERA
        },REQUEST_CAMERA); //이 메서드 호출에 의해 권한 승인 관련 팝업이 뜬다!!
    }
    public void callCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);//호출 후 결과도 가져와야 하므로...
    }

    //스토리지에 우리만의 폴더를 생성하고, 그 안에 이미지를 저장하자!!
    public void save(View view){
        File folder = new File( this.getExternalFilesDir(null) , "pic");//만들어질 디렉토리 경로 명시
        folder.mkdir(); //위에서 명시한 경로에 디렉토리 생성

        //이 디렉토리에 파일을 생성하자!!!
        String filename = System.currentTimeMillis()+".jpg"; //사진파일명 만들기!!
        File file = new File(folder, filename); //pic 이라는 디렉토리밑에 파일 생성!! empty 상태임!!

        //현재 생성된 파일은 텅비어 있으므로, 현재 사용자가 보고있는 이미지뷰로부터 사진을 채워넣어야 한다!!
        FileOutputStream fos=null;
        try {
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);//스트림을 이용하여 파일완성, 채워넣기!!
            Log.d(TAG, "파일 존재 여부 : "+file.exists());

            Toast.makeText(this, "이미지가 저장되었습니다", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //안드로이드의 비트맵 객체는 이미지 파일을 생성할 수 있는 능력

        Log.d(TAG, "getExternalFilesDir() 메서드가 가리키는 곳은?"+folder.getAbsolutePath());
    }

    public void send(View view){

    }
}