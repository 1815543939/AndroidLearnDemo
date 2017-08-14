package fengjw.com.servicebestpractice;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private String TGA = "MainActivity";

    private Button startDownload;
    private Button pauseDownload;
    private Button cancelDownload;

    private DownloadService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            downloadBinder = (DownloadService.DownloadBinder) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startDownload = (Button) findViewById(R.id.Start_Download);
        pauseDownload = (Button) findViewById(R.id.Pause_Download);
        cancelDownload = (Button) findViewById(R.id.Cancel_Download);
        startDownload.setOnClickListener(this);
        pauseDownload.setOnClickListener(this);
        cancelDownload.setOnClickListener(this);
        //Intent
        Intent intent = new Intent(MainActivity.this, DownloadService.class);
        startService(intent);
        bindService(intent, connection, BIND_AUTO_CREATE); //绑定服务
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onClick(View view) {
        if (downloadBinder == null){
            return;
        }
        switch (view.getId()){
            case R.id.Start_Download:
//                String url = "https://10.0.2.2/test.zip";
   //             String url = "https://10.0.2.2/test.zip";
                String url = "https://www.apache.org/dist/httpd/httpd-2.2.34-win32-src.zip";
                downloadBinder.startDownload(url);
                break;
            case R.id.Pause_Download:
                downloadBinder.pauseDownload();
                break;
            case R.id.Cancel_Download:
                downloadBinder.cancelDownload();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
