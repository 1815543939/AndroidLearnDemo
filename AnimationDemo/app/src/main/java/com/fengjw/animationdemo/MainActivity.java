package com.fengjw.animationdemo;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private Button mBtn;
    private Button mCancelBtn;
    private TextView mTv;
    private ValueAnimator repeatAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(this);
        mCancelBtn = (Button) findViewById(R.id.btn_cancel);
        mCancelBtn.setOnClickListener(this);
        mTv = (TextView) findViewById(R.id.tv);
    }

    private ValueAnimator valueAnimation() {
        ValueAnimator animation = new ValueAnimator().ofFloat(0f, 400f, 50f, 300f);
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float curValueFloat = (float) valueAnimator.getAnimatedValue();
                int curValue = (int) curValueFloat;
                mTv.layout(curValue, curValue, curValue + mTv.getWidth(), curValue + mTv.getHeight());
                Log.d(TAG, "curValue : " + curValue);
            }
        });

        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                Log.d(TAG, "onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Log.d(TAG, "onAnimationEnd");
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                Log.d(TAG, "onAnimationCancel");
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                Log.d(TAG, "onAnimationRepeat");
            }
        });

        animation.setRepeatCount(ValueAnimator.INFINITE);
        animation.setRepeatMode(ValueAnimator.REVERSE);
        animation.setDuration(10000);
        animation.start();
        return animation;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                // TODO 17/10/31
                //mShowTv.startAnimation(mAnimation);
                repeatAnimator = valueAnimation();
                break;
            case R.id.tv:// TODO 17/10/31
                Toast.makeText(this, "click textview!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_cancel:// TODO 17/10/31
                repeatAnimator.cancel();
                break;
            default:
                break;
        }
    }
}
