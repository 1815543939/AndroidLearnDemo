package com.fengjw.gridviewonlyonedemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by fengjw on 2018/1/14.
 */

public class MyGridView extends GridView {

    private int position = 0;
    public boolean isOnMeasure;

    private MyGridView(Context context){
        super(context);
    }

    public MyGridView(Context context, AttributeSet attrs) {//构造函数
        super(context, attrs);
  //      setChildrenDrawingOrderEnabled(true);
    }

//    public void setCurrentPosition(int pos){//刷新adapter前，在activity中调用这句传入当前选中的item在屏幕中的次序
//        this.position = pos;
//    }

//    @SuppressLint("NewApi")
//    @Override
//    protected void setChildrenDrawingOrderEnabled(boolean enabled) {
//        // TODO Auto-generated method stub
//        super.setChildrenDrawingOrderEnabled(enabled);
//    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        isOnMeasure = true;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        isOnMeasure = false;
        super.onLayout(changed, l, t, r, b);
    }
//
//    @Override
//    protected int getChildDrawingOrder(int childCount, int i) {
//        if (this.getSelectedItemPosition() != -1) {
//            if (i + this.getFirstVisiblePosition() == this.getSelectedItemPosition()) {// 这是原本要在最后一个刷新的item
//                return childCount - 1;
//            }
//            if (i == childCount - 1) {// 这是最后一个需要刷新的item
//                return this.getSelectedItemPosition() - this.getFirstVisiblePosition();
//            }
//        }
//        return i;
//    }
}
