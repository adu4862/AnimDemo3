package com.yl.animdemo.anim;

import android.view.View;

import com.nineoldandroids.animation.ValueAnimator;

/**
 * 动画的基类,使用ValueAnimator定义了动画的执行流程,具体的动画由子类实现
 * Created by Administrator on 2016/11/6 0006.
 */

public abstract class BaseAnim {
    private ValueAnimator animator;
    protected View target;//执行动画的目标view
    public BaseAnim(View target,int startvalue,int endvalue) {
        this.target = target;
        animator = ValueAnimator.ofInt(startvalue, endvalue);
        //监听动画的执行流程,具体的动画由子类实现
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int animatedValue = (int) animator.getAnimatedValue();
                doAnim(animatedValue);
            }
        });

    }

    /**
     * 由子类实现的具体的动画
     * @param animatedValue
     */
    protected abstract void doAnim(int animatedValue) ;

    /**
     * 开启动画
     * @param duration
     */
    public void start(int duration){
        animator.setDuration(duration).start();
    }
}
