package com.weather.drawerlayoutdemo;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private RelativeLayout rightLayout;
    private ContentAdapter mAdapter;

    private ImageView leftImg;
    private ImageView rightImg;
    private ListView mListView;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getActionBar().hide();

        leftImg = (ImageView) findViewById(R.id.left_img);
        rightImg = (ImageView) findViewById(R.id.right_img);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        rightLayout = (RelativeLayout) findViewById(R.id.right_rl);
        mListView = (ListView) findViewById(R.id.left_listview);
        fm = getSupportFragmentManager();

        mAdapter = new ContentAdapter(this);
        mListView.setAdapter(mAdapter);

        leftImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentTransaction ft = fm.beginTransaction();
                switch ((int) id){
                    case 1:
                        ft.replace(R.id.content_ll, new NewsFragment());
                        break;
                    case 2:
                        ft.replace(R.id.content_ll, new SubscriptionFragment());
                        break;
                    default:
                        break;
                }
                ft.commit();
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            }
        });

        rightImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.RIGHT);
            }
        });

        rightLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });

    }
}
