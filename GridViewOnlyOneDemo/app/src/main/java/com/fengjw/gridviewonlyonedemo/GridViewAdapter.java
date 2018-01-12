package com.fengjw.gridviewonlyonedemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

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
            mHolder.name = view.findViewById(R.id.tv_name);
            mHolder.src = view.findViewById(R.id.iv_src);
            view.setTag(mHolder);
        }else {
            mHolder = (ViewHolder)view.getTag();
        }

        mHolder.name.setText(mList.get(i).toString());

        return view;
    }

    private class ViewHolder{
        private TextView name;
        private ImageView src;
    }

}

