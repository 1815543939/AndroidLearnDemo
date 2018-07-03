package com.weather.drawerlayoutdemo;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private RelativeLayout rightLayout;
    private ContentAdapter mAdapter;

    private ImageView leftImg;
    private ImageView rightImg;
    private ListView mListView;
    private FragmentManager fm;

    private BottomDrawerLayout bottom_drawer_layout;
    private float density;

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

        bottom_drawer_layout = (BottomDrawerLayout) findViewById(R.id.bottom_drawer_layout);

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

        bottom_drawer_layout.setOnDrawerStatusChanged(new BottomDrawerLayout.OnDrawerStatusChanged() {
            @Override
            public void onChanged(int parentHeight, int drawerTop) {
            //LayoutParams 的类型要用设置时的view的parentview的类型
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                lp.setMargins(0,(int) (1*density), 0, parentHeight - drawerTop+(int) (1*density));
            }
        });

    }
}
