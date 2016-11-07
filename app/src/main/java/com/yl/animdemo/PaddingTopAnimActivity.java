package com.yl.animdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.yl.animdemo.anim.PaddingTopAnim;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaddingTopAnimActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.iv_safe_arrow)
    ImageView ivSafeArrow;
    @BindView(R.id.ll1)
    LinearLayout ll1;
    @BindView(R.id.ll_safe_container)
    LinearLayout llSafeContainer;
    private int maxPaddingTop;
    private int minPAddingTop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.holder_detail_1);
        ButterKnife.bind(this);
        ll1.setOnClickListener(this);
        //测量未发生动画前的paddingtop(为0)
        maxPaddingTop = llSafeContainer.getPaddingTop();
        llSafeContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            //当viewtree部署完毕调用
            @Override
            public void onGlobalLayout() {
                llSafeContainer.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                //将llSafeContainer隐藏
                minPAddingTop = -1*llSafeContainer.getMeasuredHeight();
                llSafeContainer.setPadding(llSafeContainer.getPaddingLeft(), minPAddingTop, llSafeContainer.getPaddingRight(), llSafeContainer.getPaddingBottom());
            }
        });

    }

    /**
     * view是否伸展开了,默认是false未展开
     */
    boolean isExtend =false;
    boolean isPlaying = false;

    @Override
    public void onClick(View view) {
        PaddingTopAnim anim;
        if (isPlaying) {
            return;
        }

        if (isExtend) {
            anim= new PaddingTopAnim(llSafeContainer, maxPaddingTop, minPAddingTop);

        } else {
            anim= new PaddingTopAnim(llSafeContainer, minPAddingTop, maxPaddingTop);
        }
        anim.start(400);
        isExtend = !isExtend;
        //将箭头标志反转
        ViewPropertyAnimator.animate(ivSafeArrow).rotationBy(180f).setDuration(400).setListener(new MyAnimListener()).start();

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
        public void onAnimationCancel(Animator animator) {}
        @Override
        public void onAnimationRepeat(Animator animator) {}
    }

}
