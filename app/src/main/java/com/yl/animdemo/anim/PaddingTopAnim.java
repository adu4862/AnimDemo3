package com.yl.animdemo.anim;

import android.view.View;

/**
 * Created by Administrator on 2016/11/6 0006.
 */

public class PaddingTopAnim extends BaseAnim {
    public PaddingTopAnim(View target, int startvalue, int endvalue) {
        super(target, startvalue, endvalue);
    }

    @Override
    protected void doAnim(int animatedValue) {
        target.setPadding(target.getPaddingLeft(), animatedValue, target.getPaddingRight(), target.getPaddingBottom());
    }
}
