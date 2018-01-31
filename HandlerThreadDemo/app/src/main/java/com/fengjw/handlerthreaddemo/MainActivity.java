package com.fengjw.handlerthreaddemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTvMain;
    private HandlerThread mHandlerThread;
    private Handler mHandler;
    private boolean isUpadate = true;
    public static final int MSG_UPDATE_INFO = 0x100;
    Handler mMainHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initHandlerThread();
    }

    private void initView() {
        mTvMain = (TextView) findViewById(R.id.main_tv);
        mTvMain.setTextColor(this.getResources().getColor(R.color.colorAccent));
        mTvMain.setTextSize(40);
    }

    private void initHandlerThread(){
        mHandlerThread = new HandlerThread("fengjwThread");
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                checkForUpdate();
                if (isUpadate){
                    mHandler.sendEmptyMessage(MSG_UPDATE_INFO);
                }
            }
        };
    }

    private void checkForUpdate(){
        try {
            Thread.sleep(1200);
            mMainHandler.post(new Runnable() {
                @Override
                public void run() {
                    String resutl = "实时更新中，当前股票行情 ：%d";
                    resutl = String.format(resutl, (int)(Math.random() * 5000 + 1000));
                    mTvMain.setText(resutl);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        isUpadate = true;
        super.onResume();
        mHandler.sendEmptyMessage(MSG_UPDATE_INFO);
    }

    @Override
    protected void onPause() {
        super.onPause();
        isUpadate = false;
        mHandler.removeMessages(MSG_UPDATE_INFO);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandlerThread.quit();
        mMainHandler.removeCallbacksAndMessages(null);
    }
}
