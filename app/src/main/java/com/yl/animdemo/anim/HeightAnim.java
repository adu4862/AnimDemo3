package com.yl.animdemo.anim;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/11/6 0006.
 */

public class HeightAnim extends BaseAnim {
    public HeightAnim(View target, int startvalue, int endvalue) {
        super(target, startvalue, endvalue);
    }

    @Override
    protected void doAnim(int animatedValue) {
        ViewGroup.LayoutParams params = target.getLayoutParams();
        params.height = animatedValue;
        target.setLayoutParams(params);
        //将动画变化的值通过接口暴露给外界(可用来让外界的scrollview移动)
        if (mOnHeightChangeListener != null) {
            mOnHeightChangeListener.OnHeightChange(animatedValue);
        }
    }


    private OnHeightChangeListener mOnHeightChangeListener;

    public void setmOnHeightChangeListener(OnHeightChangeListener mOnHeightChangeListener) {
        this.mOnHeightChangeListener = mOnHeightChangeListener;

    }

    private interface OnHeightChangeListener {
        void OnHeightChange(int animatedValue);
    }

}
