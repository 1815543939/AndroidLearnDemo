package com.weather.drawerlayoutdemo;

import android.content.Context;
import android.os.Parcel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengjw on 2018/7/3.
 */

public class ContentAdapter extends BaseAdapter {

    private Context mContext;
    private List<ContentModel> mList;

    public ContentAdapter(Context context){
        mContext = context;
        mList = initData();
    }

    @Override
    public int getCount() {
        if (mList != null)
            return mList.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (mList != null)
            return mList.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return mList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder hold;
        if (convertView == null){
            hold = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.content_item, null);
            hold.mImageView = (ImageView) convertView
                    .findViewById(R.id.item_imageview);
            hold.mTextView = (TextView) convertView.findViewById(R.id.item_textview);
            convertView.setTag(hold);
        }else {
            hold = (ViewHolder) convertView.getTag();
        }

        hold.mImageView.setImageResource(mList.get(position).getImageView());
        hold.mTextView.setText(mList.get(position).getText());
        return convertView;
    }

    class ViewHolder{
        public ImageView mImageView;
        public TextView mTextView;
    }

    private List<ContentModel> initData(){
        mList = new ArrayList<>();
        mList.add(new ContentModel(R.drawable.doctoradvice2, "新闻", 1));
        mList.add(new ContentModel(R.drawable.infusion_selected, "订阅", 2));
        mList.add(new ContentModel(R.drawable.mypatient_selected, "图片", 3));
        mList.add(new ContentModel(R.drawable.mywork_selected, "视频", 4));
        mList.add(new ContentModel(R.drawable.nursingcareplan2, "跟帖", 5));
        mList.add(new ContentModel(R.drawable.personal_selected, "投票", 6));
        return mList;
    }

}
