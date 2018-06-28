package test.install;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button install,delete;

    private static final String packageName = "com.mstar.tv.tvplayer.ui";

    private EditText pkgBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pkgBtn = (EditText) findViewById(R.id.pkgname_btn);
    }

    @Override
    public void onClick(final View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                switch (v.getId()){
                    case R.id.install:
                        InstallUtil.J_install(MainActivity.this,"MTvPlayer",packageName);//静默安装
                        break;
                    case R.id.delete:
                        InstallUtil.J_uninstall(MainActivity.this,packageName);//静默卸载
                        break;
                    case R.id.install2:
                        InstallUtil.install(MainActivity.this,packageName);//普通安装
                        break;
                    case R.id.uninstall2:
                        InstallUtil.uninstall(MainActivity.this,packageName);//普通卸载
                        break;
                    case R.id.install_root:
                        InstallUtil.J_installRoot("MTvPlayer");//需要root的静默安装
                        break;
                    case R.id.uninstall_root:
                        InstallUtil.J_uninstallRoot(packageName);
                        break;
                    case R.id.install3:
                        InstallUtil.JF_install(MainActivity.this,"MTvPlayer",packageName);//反射方式
                        break;
                    case R.id.uninstall3:
                        InstallUtil.JF_uninstall(MainActivity.this, pkgBtn.getText().toString());//反射方式
                        break;
                }
            }
        }).start();

    }


}
