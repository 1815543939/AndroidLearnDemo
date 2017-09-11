package fengjw.com.servicetest;

import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.WindowManager;

public class MyService extends Service {
    private final static String TGA = "MyService";
    private String str = "您有新的应用需要更新\n请问是否进行更新？";
    public MyService() {
    }

    private final static int GET_HANDLER_ONE = 1;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GET_HANDLER_ONE:
                    try {
                        Log.d(TGA, "index jump!");
                        AlertDialog.Builder builder = new AlertDialog.Builder(MyService.this);
                        builder.setTitle("更新提醒");
                        builder.setMessage(str);
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                Intent intent = new Intent(MyService.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        });
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                        dialog.show();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    break;
                default:
                    break;
            }
        }
    };


    private DownloadBinder mBinder = new DownloadBinder();

    class DownloadBinder extends Binder {
        public void startDownload(){
            Log.d(TGA, "StartDownload executed");
        }

        public int getProgress(){
            Log.d(TGA, "getProgress executed");
            return 0;
        }
    }



    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.d(TGA, "IBinder");
        //throw new UnsupportedOperationException("Not yet implemented");
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TGA, "onCreate");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TGA, "handler");
                mHandler.sendEmptyMessage(GET_HANDLER_ONE);
            }
        }).start();

    }

    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {
        Log.d(TGA, "onStartCommand");
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TGA, "onDestroy");
        super.onDestroy();
    }
}
