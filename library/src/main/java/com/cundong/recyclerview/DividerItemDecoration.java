package com.cundong.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by dongye on 15/11/13.
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    private Drawable mDivider;

    private HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter;

    private int mOrientation;

    private int mSpacing = -1;

    public DividerItemDecoration(Context context, int orientation, HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        setOrientation(orientation);
        this.mHeaderAndFooterRecyclerViewAdapter = mHeaderAndFooterRecyclerViewAdapter;
    }

    public DividerItemDecoration(Context context, int orientation, int resId, HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mDivider = context.getResources().getDrawable(resId, context.getTheme());
        } else {
            mDivider = context.getResources().getDrawable(resId);
        }
        setOrientation(orientation);
        this.mHeaderAndFooterRecyclerViewAdapter = mHeaderAndFooterRecyclerViewAdapter;
    }

    //为了避免与上面的构造方法重名 改变啦参数的位置  注意!!!
    public DividerItemDecoration(Context context, HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter, int orientation, int spacing) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        mSpacing = spacing;
        setOrientation(orientation);
        this.mHeaderAndFooterRecyclerViewAdapter = mHeaderAndFooterRecyclerViewAdapter;
    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent) {
        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }

    }

    public void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            if (mHeaderAndFooterRecyclerViewAdapter.getHeaderView() != null && mHeaderAndFooterRecyclerViewAdapter.getHeaderView().contains(child)) {
                continue;
            }
            if (mHeaderAndFooterRecyclerViewAdapter.getFooterView() != null && mHeaderAndFooterRecyclerViewAdapter.getFooterView().contains(child)) {
                continue;
            }
            android.support.v7.widget.RecyclerView v = new android.support.v7.widget.RecyclerView(parent.getContext());

            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            if (mHeaderAndFooterRecyclerViewAdapter.getHeaderView() != null && mHeaderAndFooterRecyclerViewAdapter.getHeaderView().contains(child)) {
                continue;
            }
            if (mHeaderAndFooterRecyclerViewAdapter.getFooterView() != null && mHeaderAndFooterRecyclerViewAdapter.getFooterView().contains(child)) {
                continue;
            }
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        if (isDividerShow(itemPosition)) {
            if (mOrientation == VERTICAL_LIST) {
                mSpacing = (-1 != mSpacing) ? mSpacing : mDivider.getIntrinsicHeight();
                outRect.set(0, 0, 0, mSpacing);
            } else {
                mSpacing = (-1 != mSpacing) ? mSpacing : mDivider.getIntrinsicWidth();
                outRect.set(0, 0, mSpacing, 0);
            }
        }
    }

    public boolean isDividerShow(int index) {
        return !(mHeaderAndFooterRecyclerViewAdapter.isHeader(index) || mHeaderAndFooterRecyclerViewAdapter.isFooter(index));
    }
}

