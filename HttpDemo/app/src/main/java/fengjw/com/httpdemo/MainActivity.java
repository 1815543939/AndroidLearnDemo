package fengjw.com.httpdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VideoServiceTes videoServiceTes = new VideoServiceTes();
        videoServiceTes.testLocalIpAndMac();

        Button button = (Button) findViewById(R.id.btn_http);
        button.setOnClickListener(this);
        Button button1 = (Button) findViewById(R.id.btn_okhttp);
        button1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_http:
                Intent intent = new Intent(MainActivity.this, HttpConnectionActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_okhttp:
                Intent intent1 = new Intent(MainActivity.this, OKHttpActivity.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }
}
