package com.weather.drawerlayoutdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;


public class CustomImgContainer extends ViewGroup{

    public CustomImgContainer(Context context) {
        super(context);
    }

    public CustomImgContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomImgContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs){
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * 获得此ViewGroup上级容器为其推荐的宽和高，以及计算模式
         */
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        Log.d("fengjw", "widthMode : " + widthMode);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        Log.d("fengjw", "heightMode : " + heightMode + "\n\n\n");
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);


        // 计算出所有的childView的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        /**
         * 记录如果是wrap_content是设置的宽和高
         */
        int width = 0;
        int height = 0;

        int cCount = getChildCount();//get current child

        int cWidth = 0;
        int cHeight = 0;
        MarginLayoutParams cParams = null;

        // 用于计算左边两个childView的高度
        int lHeight = 0;
        // 用于计算右边两个childView的高度，最终高度取二者之间大值
        int rHeight = 0;

        // 用于计算上边两个childView的宽度
        int tWidth = 0;
        // 用于计算下面两个childiew的宽度，最终宽度取二者之间大值
        int bWidth = 0;

        /**
         * 根据childView计算的出的宽和高，以及设置的margin计算容器的宽和高，主要用于容器是warp_content时
         */
        for (int i = 0; i < cCount; i++)
        {
            View childView = getChildAt(i);
            cWidth = childView.getMeasuredWidth();
            cHeight = childView.getMeasuredHeight();
            cParams = (MarginLayoutParams) childView.getLayoutParams();//get child params

            // 上面两个childView
            if (i == 0 || i == 1)
            {
                Log.d("fengjw", "i = " + i + "cWidth : " + cWidth);
                tWidth += cWidth + cParams.leftMargin + cParams.rightMargin;
                Log.d("fengjw", "tWidth : " + tWidth + " leftMargin : " + cParams.leftMargin
                    + " rightMargin : " + cParams.rightMargin);
            }

            if (i == 2 || i == 3)
            {
                Log.d("fengjw", "i = " + i + "cWidth : " + cWidth);
                bWidth += cWidth + cParams.leftMargin + cParams.rightMargin;
                Log.d("fengjw", "bWidth : " + bWidth + " leftMargin : " + cParams.leftMargin
                        + " rightMargin : " + cParams.rightMargin);
            }

            if (i == 0 || i == 2)
            {
                Log.d("fengjw", "i = " + i + "cHeight : " + cHeight);
                lHeight += cHeight + cParams.topMargin + cParams.bottomMargin;
                Log.d("fengjw", "lHeight : " + lHeight + " topMargin : " + cParams.topMargin
                        + " bottomMargin : " + cParams.bottomMargin);
            }

            if (i == 1 || i == 3)
            {
                Log.d("fengjw", "i = " + i + "cHeight : " + cHeight);
                rHeight += cHeight + cParams.topMargin + cParams.bottomMargin;
                Log.d("fengjw", "rHeight : " + rHeight + " topMargin : " + cParams.topMargin
                        + " bottomMargin : " + cParams.bottomMargin);
            }

        }

        width = Math.max(tWidth, bWidth);
        height = Math.max(lHeight, rHeight);

        /**
         * 如果是wrap_content设置为我们计算的值
         * 否则：直接设置为父容器计算的值
         */
        setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? sizeWidth
                : width, (heightMode == MeasureSpec.EXACTLY) ? sizeHeight
                : height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int cCount = getChildCount();
        int cWidth = 0;
        int cHeight = 0;
        MarginLayoutParams cParams = null;
        /**
         * 遍历所有childView根据其宽和高，以及margin进行布局
         */
        for (int i = 0; i < cCount; i++)
        {
            View childView = getChildAt(i);
            cWidth = childView.getMeasuredWidth();
            cHeight = childView.getMeasuredHeight();
            cParams = (MarginLayoutParams) childView.getLayoutParams();

            int cl = 0, ct = 0, cr = 0, cb = 0;

            switch (i)
            {
                case 0:
                    cl = cParams.leftMargin;
                    ct = cParams.topMargin;
                    Log.d("fengjw", "0 : cl : " + cParams.leftMargin
                            + " ct : " + cParams.rightMargin);
                    break;
                case 1:
//                    cl = getWidth() - cWidth - cParams.leftMargin
//                            - cParams.rightMargin;
                    Log.d("fengjw", "1 : getWidth : " + getWidth() + " cWidth : " + cWidth);
                    cl = getWidth() - cWidth;
                    ct = cParams.topMargin;
                    Log.d("fengjw", "1 : cl : " + cl + " ct : " + ct);
                    break;
                case 2:
                    cl = cParams.leftMargin;
                    Log.d("fengjw", "2 : cl : " + cl);
//                    ct = getHeight() - cHeight - cParams.bottomMargin;
                    ct = getHeight() - cHeight;
                    Log.d("fengjw", "2 : getHeight : " + getHeight() + " cHeight : " + cHeight);
                    break;
                case 3:
//                    cl = getWidth() - cWidth - cParams.leftMargin
//                            - cParams.rightMargin;
                    cl = getWidth() - cWidth;
                    ct = getHeight() - cHeight - cParams.bottomMargin;
                    Log.d("fengjw", "3 : getWidth : " + getWidth() + " cWidth : " + cWidth);
                    Log.d("fengjw", "3 : getHeight : " + getHeight() + " cHeight : " + cHeight
                        + " bottomMargin : " + cParams.bottomMargin);
                    break;

            }
            cr = cl + cWidth;
            Log.d("fengjw", i + " : cl : " + cl + " cWidth : " + cWidth + " cr : " + cr);
            cb = cHeight + ct;
            Log.d("fengjw", i + " : cb : " + cb + " cHeight : " + cHeight + " ct : " + ct);
            childView.layout(cl, ct, cr, cb);//four vertices
        }

    }
}
