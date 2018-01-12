package com.fengjw.gridviewonlyonedemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private GridView mGridview;
    private List<String> mList;
    private GridViewAdapter mAdapter;

    public static boolean JUMP_TO_FIRSTORLAST = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setData();
        setGridviewData();
    }

    private void initView() {
        mGridview = (GridView) findViewById(R.id.gridview);
        mGridview.setOnKeyListener(mGridViewKeyListener);
        mGridview.setOnItemSelectedListener(mSelectedListener);

    }

    private void setData(){
        mList = new ArrayList<>();
        mList.add("冯建伟的U盘");
        mList.add("浩哥的U盘");
        mList.add("毛哥的U盘");
        mList.add("朱哥的U盘");
        mList.add("伟哥的U盘");
    }

    private void setGridviewData(){
        int size = mList.size();
        int length = 100;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = (int) (size * (length + 4) * density);
        int itemWidth = (int) (length * density);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.MATCH_PARENT
        );
        mGridview.setLayoutParams(params);
        mGridview.setColumnWidth(itemWidth);
        mGridview.setHorizontalSpacing(5);
        mGridview.setStretchMode(GridView.NO_STRETCH);
        mGridview.setNumColumns(size);

        //
        mAdapter = new GridViewAdapter(this, mList);
        mGridview.setAdapter(mAdapter);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        LogUtil.dThread("JUMP_TO_FIRSTORLAST : " + JUMP_TO_FIRSTORLAST);
        if (JUMP_TO_FIRSTORLAST) {
            if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                LogUtil.dThread("KEYCODE_DPAD_RIGHT");
                mGridview.setSelection(0);
            }
            if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
                mGridview.setSelection(mList.size() - 1);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private AdapterView.OnItemSelectedListener mSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                LogUtil.d("OnItemSelected");
                LogUtil.dThread("OnItemSelected : " + i);
                LogUtil.d("mList.size : " + mList.size());
                if (i == mList.size() - 1 || i == 0){
                    JUMP_TO_FIRSTORLAST = true;
                }else {
                    JUMP_TO_FIRSTORLAST = false;
                }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    };

    private View.OnKeyListener mGridViewKeyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            LogUtil.dThread("onKey : " + keyEvent);
            LogUtil.dThread("position : " + i);
            return false;
        }
    };

}
