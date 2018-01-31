package com.fengjw.gridviewonlyonedemo;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

/**
 * Created by fengjw on 2018/1/12.
 */

public class GridViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mList;

    public GridViewAdapter(Context context, List<String> list){
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        ViewHolder mHolder = null;
        if (view == null){
            mHolder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.list_item, null);
            mHolder.name = view.findViewById(R.id.item_name);
            mHolder.src = view.findViewById(R.id.item_image);
            mHolder.mLayout = view.findViewById(R.id.item_bg);
            view.setTag(mHolder);
        }else {
            mHolder = (ViewHolder)view.getTag();
        }

        if (((MyGridView)viewGroup).isOnMeasure){
            LogUtil.d("Measure !");
            return view;
        }

        Drawable bg = null;
        Drawable image = null;
        Resources resources = mContext.getResources();

        Random random = new Random();
        int count = random.nextInt(4);
        LogUtil.d("count : " + count);
        switch (count){
            case 0:
                //bg = resources.getDrawable(R.drawable.bg_home_activity_all);
                image = resources.getDrawable(R.drawable.home_update);
                break;
            case 1:
                //bg = resources.getDrawable(R.drawable.bg_home_activity_music);
                image = resources.getDrawable(R.drawable.home_file);
                break;
            case 2:
                //bg = resources.getDrawable(R.drawable.bg_home_activity_picture);
                image = resources.getDrawable(R.drawable.home_memory);
                break;
            case 3:
                //bg = resources.getDrawable(R.drawable.bg_home_activity_video);
                image = resources.getDrawable(R.drawable.home_recentlist);
                break;
            default:
                break;
        }
        //mHolder.mLayout.setBackground(bg);
        mHolder.src.setImageDrawable(image);
        mHolder.name.setText(mList.get(i).toString());
        return view;
    }

    private class ViewHolder{
        private TextView name;
        private ImageView src;
        private LinearLayout mLayout;
    }

}

