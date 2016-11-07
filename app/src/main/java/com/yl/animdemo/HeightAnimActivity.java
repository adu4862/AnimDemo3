package com.yl.animdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.yl.animdemo.anim.HeightAnim;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/6 0006.
 */

public class HeightAnimActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.iv_des_arrow)
    ImageView ivDesArrow;
    @BindView(R.id.ll2)
    LinearLayout ll2;
    private int minHeight;
    private int maxHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.holder_detail_2);
        ButterKnife.bind(this);
        ll2.setOnClickListener(this);
        /*//测量缩进到5行时
        tvDes.setMaxLines(10000);
        tvDes.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                tvDes.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                minHeight = tvDes.getMeasuredHeight();
                tvDes.setMaxLines(5);//让textview完全显示只要参数超过实际行数就行
                tvDes.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        maxHeight = tvDes.getMeasuredHeight();
                        //缩进到只有5行
                        ViewGroup.LayoutParams params = tvDes.getLayoutParams();
                        params.height = minHeight;
                        tvDes.setLayoutParams(params);
                    }
                });

            }
        });*/
        tvDes.setMaxLines(5);
        tvDes.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                tvDes.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                minHeight = tvDes.getHeight();
                tvDes.setMaxLines(1000);
                tvDes.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        tvDes.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        maxHeight = tvDes.getHeight();
                        //让tvDes缩进到5行
                        ViewGroup.LayoutParams params = tvDes.getLayoutParams();
                        params.height = minHeight;
                        tvDes.setLayoutParams(params);
                    }
                });
            }
        });

    }

    boolean isExtend = false;
    boolean isPlaying = false;

    @Override
    public void onClick(View v) {
       // Log.e(TAG, "onClick: 测试点击事件-.-isExtend: " + isExtend);
        if (isPlaying) {
            return;
        }
        HeightAnim anim = null;

        if (isExtend) {
            //执行收缩动画
            anim = new HeightAnim(tvDes,maxHeight,minHeight);
        } else {
            //执行伸展动画
            anim = new HeightAnim(tvDes,minHeight,maxHeight);

        }
        anim.start(350);


        //将标记置为反值
        isExtend = !isExtend;
        //让箭头反转
        ViewPropertyAnimator.animate(ivDesArrow).rotationBy(180).setListener(new MyAnimListener()).setDuration(350).start();
    }

    class MyAnimListener implements Animator.AnimatorListener {

        @Override
        public void onAnimationStart(Animator animator) {
            isPlaying = true;
        }

        @Override
        public void onAnimationEnd(Animator animator) {
            isPlaying = false;
        }

        @Override
        public void onAnimationCancel(Animator animator) {
        }

        @Override
        public void onAnimationRepeat(Animator animator) {
        }
    }


}
