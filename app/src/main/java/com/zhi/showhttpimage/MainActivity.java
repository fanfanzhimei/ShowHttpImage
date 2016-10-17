package com.zhi.showhttpimage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends Activity {
    private static final int MESSAGE_SHOW = 0x1;

    private EditText mEtUrl;
    private Button mBtnShow;
    private ImageView mImage;

    private Bitmap bitmap;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
           switch (msg.what){
               case MESSAGE_SHOW:
                   if (null != bitmap) {
                       mImage.setImageBitmap(bitmap);
                   }
                   break;
           }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initEvent();
    }

    private void initViews() {
        mEtUrl = (EditText) findViewById(R.id.et_url);
        mBtnShow = (Button) findViewById(R.id.btn_show);
        mImage = (ImageView) findViewById(R.id.image);
    }

    private void initEvent() {
        mBtnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetHttpThread thread = new GetHttpThread();
                thread.start();
            }
        });
    }

    class GetHttpThread extends Thread{
        @Override
        public void run() {
            try {
                String url = mEtUrl.getText().toString().trim();
                bitmap = HttpUtils.getUrl(url);
                mHandler.sendEmptyMessage(MESSAGE_SHOW);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}