package com.gxtc.yyj.newyin.mvp.ui.behavior;

import android.animation.Animator;
import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;

/**
 * Created by Jam on 2017/7/6.
 */

public class FloatingActionButtonBehavior extends CoordinatorLayout.Behavior<View> {
    private static final String TAG = "FloatingActionButtonBeh";
    private float viewY;//控件距离coordinatorLayout底部距离
    private boolean isAnimate;//动画是否在进行

    public FloatingActionButtonBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //在嵌套滑动开始前回调

//    @Override
//    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
//        if (child.getVisibility() == View.VISIBLE && viewY == 0) {
//            //获取控件距离父布局（coordinatorLayout）底部距离
//            viewY = coordinatorLayout.getHeight() - child.getY();
//        }
//
//        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;//判断是否竖直滚动
//    }


    //在嵌套滑动进行时，对象消费滚动距离前回调
//    @Override
//    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dx, int dy, int[] consumed) {
//        //dy大于0是向上滚动 小于0是向下滚动
//        if (dy >= 0 && !isAnimate && child.getVisibility() == View.VISIBLE) {
//            hide(child);
//            //写成Gone fb不起作用了滑下去就没了 查看源码在coordinatorLayout中如果view为gone就不会被调用到这个onNestedPreScroll方法
//        } else if (dy < 0 && !isAnimate && child.getVisibility() == View.INVISIBLE) {
//            show(child);
//        }
//    }

    //隐藏时的动画
    private void hide(final View view) {
        ViewPropertyAnimator animator = view.animate().translationY(viewY).setDuration(200);

        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                isAnimate = true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(View.INVISIBLE);
                isAnimate = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                show(view);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        animator.start();
    }

    //显示时的动画
    private void show(final View view) {
        ViewPropertyAnimator animator = view.animate().translationY(0).setDuration(200);
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                view.setVisibility(View.VISIBLE);
                isAnimate = true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                isAnimate = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                hide(view);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        animator.start();
    }


    //这两个方法是关联的 一个是依赖哪个View

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    //当依赖的View变化的时候

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, final View child, View dependency) {
        int height = dependency.getHeight();
//        if (child.getVisibility() == View.VISIBLE && viewY == 0) {
//            viewY = parent.getHeight() - child.getY();//得到距离底部的高度
//        }
//        int translationY = (int) (Math.abs(dependency.getTop() * 1f / height * viewY) + 0.5f);
//        child.setTranslationY(translationY);
        float fraction = Math.abs(dependency.getTop() * 1f / height);
        child.setScaleY(1-fraction);
        child.setScaleX(1-fraction);
        child.setAlpha(1-fraction);
        return true;
    }
}
