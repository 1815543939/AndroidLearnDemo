package com.fengjw.fragmentdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button mBtn;
    private FragmentManager mManager;
    private MyFragment mFragment1;
    private MyFragment2 mFragment2;
    private boolean click = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mFragment1 = new MyFragment();
        mFragment2 = new MyFragment2();
        mManager = getSupportFragmentManager();
        FragmentTransaction transaction = mManager.beginTransaction();
        transaction.add(R.id.fragment, mFragment1).commit();
    }

    private void initView() {
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                if (click){
                    click = false;
                    FragmentTransaction transaction = mManager.beginTransaction();
                    transaction.replace(R.id.fragment, mFragment1).commit();
                }else {
                    click = true;
                    FragmentTransaction transaction = mManager.beginTransaction();
                    transaction.replace(R.id.fragment, mFragment2).commit();
                }
                break;
            default:
                break;
        }
    }
}
