package com.gxtc.yyj.newyin.common.utils;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ASUS on 2017/6/30.
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private int mDividerHeight = 1;
    private Paint mPaint;
    private int mDividerColor = 0;//默认为0透明
    private int mFirstItemToTop = 0;
    private int mFirstItemToTopColor = 0;//默认透明
    private boolean mHasFooter;


    public DividerItemDecoration(int dividerHeight) {
        this(dividerHeight, 0);
    }

    public DividerItemDecoration(int dividerHeight, @ColorInt int dividerColor) {
        this(0, 0, dividerHeight, dividerColor, false);
    }

    public DividerItemDecoration(int firstItemToTop, int dividerHeight, boolean hasFooter) {
        this(firstItemToTop, 0, dividerHeight, 0, hasFooter);
    }

    public DividerItemDecoration(int firstItemToTop, @ColorInt int firstItemToTopColor, int dividerHeight, @ColorInt int dividerColor, boolean hasFooter) {
        this.mFirstItemToTop = firstItemToTop;//第一个item距离RecyclerView顶部的距离
        this.mDividerHeight = dividerHeight;//item之间的间距
        this.mDividerColor = dividerColor;//item间间距的颜色
        this.mFirstItemToTopColor = firstItemToTopColor;//第一个item距离顶部的颜色
        this.mHasFooter = hasFooter;
        setUp(dividerColor);
    }


    private void setUp(@ColorInt int color) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(color);
    }


    /**
     * @param outRect 查看源码在RecyclerView中初始是一个全部都为0的Rect 而我们可以对这个Rect的左上右下进行赋值来装饰RecyclerView的item
     * @param view    就是itemView
     * @param parent  RecyclerView
     * @param state   状态
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view) != 0) {//获取view在adapter中的位置 当不为0的时候
            outRect.top = mDividerHeight;
            if (mHasFooter){
                if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1)
                    outRect.top = 0;
            }
        } else {
            outRect.top = mFirstItemToTop;
        }
    }

    /**
     * @param c      画布
     * @param parent RecyclerView
     * @param state  状态
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int itemCount = parent.getChildCount();
        for (int i = 0; i < itemCount; i++) {
            View view = parent.getChildAt(i);
            int index = parent.getChildAdapterPosition(view);
            float left = parent.getPaddingLeft();
            float right = parent.getWidth() - parent.getPaddingRight();
            if (index == 0) {
                mPaint.setColor(mFirstItemToTopColor);
                c.drawRect(left, view.getTop() - mFirstItemToTop, right, view.getTop(), mPaint);
            } else {
                mPaint.setColor(mDividerColor);
                float top = view.getTop() - mDividerHeight;
                float bottom = view.getTop();
                c.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }
}
