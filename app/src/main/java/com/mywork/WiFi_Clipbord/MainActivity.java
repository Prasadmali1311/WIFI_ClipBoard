package com.mywork.WiFi_Clipbord;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



public class MainActivity extends AppCompatActivity {

    EditText edTxt;
    public MyThread myThread;
    MyThread thread2;
    public String msg1="p1";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        edTxt = findViewById(R.id.edTxt);
        myThread = new MyThread();
        thread2 = new MyThread();

        //edTxt.setText("prasad");
        //new Thread(myThread).start();
        //prasad();
    }

    public class MyThread implements Runnable {
        private volatile String msg = "";
        Socket socket;

        @Override
        public void run(){
            edTxt.setText("Processing...");
            ClipboardManager clipboardManager;
            clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clipData = clipboardManager.getPrimaryClip();
            ClipData.Item item = clipData.getItemAt(0);
            msg1 = item.getText().toString();
            //msg1= name;

            try {
                //String msg = name;
                ServerSocket ss = new ServerSocket(1311);
                //dos = new DataOutputStream(socket.getOutputStream());
                Socket s = ss.accept();
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                dos.writeUTF(msg1);
                dos.close();
                dos.flush();
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            edTxt.setText("Done");
            //prasad();
        }

//        public void run3() {
//
//                ClipboardManager clipboardManager;
//                clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
//                ClipData clipData = clipboardManager.getPrimaryClip();
//                ClipData.Item item = clipData.getItemAt(0);
//                msg1 = item.getText().toString();
//                //msg1= name;
//                //edTxt.setText(msg1);
//                edTxt.setText("Done");
//
//            try {
//                //String msg = name;
//                ServerSocket ss = new ServerSocket(1311);
//                //dos = new DataOutputStream(socket.getOutputStream());
//                Socket s = ss.accept();
//                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
//                dos.writeUTF(msg1);
//                dos.close();
//                dos.flush();
//                ss.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }


//        public void run2(){
//            ClipboardManager clipboardManager;
//            clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
//            ClipData clipData = clipboardManager.getPrimaryClip();
//            ClipData.Item item = clipData.getItemAt(0);
//            msg1 = item.getText().toString();
//            //msg1= name;
//            //edTxt.setText(msg1);
//            edTxt.setText("Done");
//
//            prasad();
//        }
//        public void sendMsgParam(String msg){
//            this.msg = msg;
//            run3();
//        }
//        public void receiveMsgParam(){
//            run2();
//        }
    }









    public void btnclickSnd(View v){
        myThread.run();
    }
    public void btnclickRec(View v){
        edTxt.setText("");
    }
    }
