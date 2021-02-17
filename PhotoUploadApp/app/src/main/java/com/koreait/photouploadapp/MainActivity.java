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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    String TAG=this.getClass().getName();
    public static final int REQUEST_CAMERA=1;
    ImageView imageView;
    Bitmap bitmap; //현재 보고 잇는 사진에 대한 비트맵
    File file;

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
        file = new File(folder, filename); //pic 이라는 디렉토리밑에 파일 생성!! empty 상태임!!

        //현재 생성된 파일은 텅비어 있으므로, 현재 사용자가 보고있는 이미지뷰로부터 사진을 채워넣어야 한다!!
        FileOutputStream fos=null;
        try {
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);//스트림을 이용하여 파일완성, 채워넣기!!

            //컴프레스 이후에 파일이 물리적으로 존재하게 됨..
            Log.d(TAG, "파일 존재 여부 : "+file.exists());

            Toast.makeText(this, "이미지가 저장되었습니다", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //안드로이드의 비트맵 객체는 이미지 파일을 생성할 수 있는 능력

        Log.d(TAG, "getExternalFilesDir() 메서드가 가리키는 곳은?"+folder.getAbsolutePath());
    }

    public void send(View view){
        Thread thread = new Thread(){
            public void run() {
                upload2();
            }
        };
        thread.start();
    }

    /*기존의 HttpURLConnection 을 이용하여 파일까지 서버에 업로드해본다!!
        HTTP 프로토콜의 multipart/form-data 형식을 갖춰어야 한다..즉 크롬 브라우저의 역할을
        안드로이드 코드에서 구현해야 한다...
    */
    public void upload() {
        //바운더리 값은 아무거나 상관이 없으나 반드시 바운더리값을 기준으로 앞,뒤로 두개의 하이픈은 존재해야 한다..
        String boundary="sdjflksdfjlkdsfjlksadflsaj";
        String line="/r/n"; //줄바꿈  \r은 케리지리턴 즉 커서를 처음 위치로 오게..
        BufferedWriter buffw=null; //파일의 정보를 전송할 스트림
        OutputStream os=null; //파일을 전송할 스트림
        FileInputStream fis=null;

        try {
            URL url = new URL("http://192.168.35.96:9999/admin/photo");
            HttpURLConnection con =(HttpURLConnection) url.openConnection();

            //요청 헤더값 구성

            //다른 일반 텍스트 파라미터들과 구분하기 위해서 정해놓은 경계규칙(w3c에서...)
            con.setRequestProperty("Content-Type","multipart/form-data;charset=utf-8; boundary="+boundary);
            con.setRequestMethod("POST");
            con.setDoOutput(true);//서버에 보낼때..

            buffw = new BufferedWriter(new OutputStreamWriter( con.getOutputStream() , "UTF-8"));
            //요청을 하기 전에, 필요한 파라미터를 구성하되, 출력스트림으로 처리하자!!
            //텍스트 파라미터 전송을 위한 폼 구성

            //버퍼에 쌓기!   flush()할때 출력이 발생
            buffw.append("--"+boundary).append(line);
            buffw.append("Content-Type:text/plain;charset=utf-8").append(line);
            buffw.append("Content-Disposition:form-data; name=\"title\"").append(line);
            buffw.append(line);
            buffw.append("연습이에용^^").append(line);
            buffw.flush();

            //파일 파라미터 전송을 위한 폼 구성
            Log.d(TAG, "전송직전 파일명은 "+file.getName());

            buffw.append("--"+boundary).append(line);
            buffw.append("Content-Disposition: form-data; name=\"myFile\"; filename=\"" + file.getName() + "\"").append(line);
            buffw.append("Content-Type: " + URLConnection.guessContentTypeFromName(file.getName())).append(line);
            buffw.append("Content-Transfer-Encoding: binary").append(line);
            buffw.append(line);
            buffw.flush();

            //서버로 전송할 파일의 데이터를 읽어들여, 스트림으로 출력하자!!
            fis = new FileInputStream(file);//전송할 파일을 읽기위한 스트림!!
            os = con.getOutputStream();

            int data=-1;
            byte[] buffer = new byte[1024];

            while(true){
                data = fis.read(buffer); //한 알갱이를 읽어들여, 바구니인 byte배열에 쌓아놓자!
                if(data==-1)break;  //1M  = 1024*1024
                os.write(buffer);//한 배열씩 서버에 스트림으로 전송
            }
            os.flush();
            buffw.append(line);
            buffw.append("--"+boundary+"--").append(line); //파일파라미터에 대한 경계 종료

            int code = con.getResponseCode(); //요청 및 응답이 발생하는 시점..
            Log.d(TAG, "서버의 응답코드"+code);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void upload2(){
        String boundary = "^-----^";
        String LINE_FEED = "\r\n";
        String charset = "UTF-8";
        OutputStream outputStream;
        PrintWriter writer;

        JSONObject result = null;
        try{

            URL url = new URL("http://192.168.35.96:8888/admin/photo");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Content-Type", "multipart/form-data;charset=utf-8;boundary=" + boundary);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setConnectTimeout(15000);

            outputStream = connection.getOutputStream();
            writer = new PrintWriter(new OutputStreamWriter(outputStream, charset), true);

            /** Body에 데이터를 넣어줘야 할경우 없으면 Pass **/
            writer.append("--" + boundary).append(LINE_FEED);
            writer.append("Content-Disposition: form-data; name=\"title\"").append(LINE_FEED);
            writer.append("Content-Type: text/plain; charset=" + charset).append(LINE_FEED);
            writer.append(LINE_FEED);
            writer.append("batman").append(LINE_FEED);
            writer.flush();

            /** 파일 데이터를 넣는 부분**/
            Log.d(TAG, "업로드할 파일명"+file.getName());

            writer.append("--" + boundary).append(LINE_FEED);
            writer.append("Content-Disposition: form-data; name=\"myFile\"; filename=\"" + file.getName() + "\"").append(LINE_FEED);
            writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(file.getName())).append(LINE_FEED);
            writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
            writer.append(LINE_FEED);
            writer.flush();

            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int bytesRead = -1;

            while (true) {
                bytesRead = fis.read(buffer);
                if(bytesRead==-1)break;
                outputStream.write(buffer);
            }
            outputStream.flush();
            fis.close();
            writer.append(LINE_FEED);
            writer.flush();

            writer.append("--" + boundary + "--").append(LINE_FEED);
            writer.close();

            int responseCode = connection.getResponseCode();
            Log.d(TAG, "요청 시도 결과 responseCode " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                try {
                    result = new JSONObject(response.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                result = new JSONObject(response.toString());
            }

        } catch (ConnectException e) {
            Log.e(TAG, "ConnectException");
            e.printStackTrace();


        } catch (Exception e){
            e.printStackTrace();
        }
    }
}