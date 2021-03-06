package com.cundong.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Created by dongye on 15/11/13.
 */
public class RecyclerViewUtils {

    /**
     * 添加HeaderView
     *
     * @param recyclerView
     * @param view
     */
    public static void addHeaderView(RecyclerView recyclerView, View view) {
        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();

        if (outerAdapter == null || !(outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter)) {
            return;
        }

        HeaderAndFooterRecyclerViewAdapter headerAndFooterAdapter = (HeaderAndFooterRecyclerViewAdapter) outerAdapter;
//        if (headerAndFooterAdapter.getHeaderViewsCount() == 0) {
        headerAndFooterAdapter.addHeaderView(view);
//        }
    }

    /**
     * 设置FooterView
     *
     * @param recyclerView
     * @param view
     */
    public static void addFooterView(RecyclerView recyclerView, View view) {

        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();

        if (outerAdapter == null || !(outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter)) {
            return;
        }

        HeaderAndFooterRecyclerViewAdapter headerAndFooterAdapter = (HeaderAndFooterRecyclerViewAdapter) outerAdapter;
        headerAndFooterAdapter.addFooterView(view);
    }

    /**
     * 移除FooterView
     *
     * @param recyclerView
     */
    public static void removeFooterView(RecyclerView recyclerView, View view) {

        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();

        if (view != null && outerAdapter != null && outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter) {

            int footerViewCounter = ((HeaderAndFooterRecyclerViewAdapter) outerAdapter).getFooterViewsCount();
            if (footerViewCounter > 0) {
                List<View> footerView = ((HeaderAndFooterRecyclerViewAdapter) outerAdapter).getFooterView();
                if (footerView.contains(view)) {
                    ((HeaderAndFooterRecyclerViewAdapter) outerAdapter).removeFooterView(view);
                }
            }
        }
    }

    /**
     * 移除HeaderView
     *
     * @param recyclerView
     */
    public static void removeHeaderView(RecyclerView recyclerView, View view) {

        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();

        if (outerAdapter != null && outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter) {

            int headerViewCounter = ((HeaderAndFooterRecyclerViewAdapter) outerAdapter).getHeaderViewsCount();
            if (headerViewCounter > 0) {
                List<View> headerView = ((HeaderAndFooterRecyclerViewAdapter) outerAdapter).getHeaderView();
                if (headerView.contains(view)) {
                    ((HeaderAndFooterRecyclerViewAdapter) outerAdapter).removeHeaderView(view);
                }
            }
        }
    }

    /**
     * 请使用本方法替代RecyclerView.ViewHolder的getLayoutPosition()方法
     *
     * @param recyclerView
     * @param holder
     * @return
     */
    public static int getLayoutPosition(RecyclerView recyclerView, RecyclerView.ViewHolder holder) {
        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();
        if (outerAdapter != null && outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter) {

            int headerViewCounter = ((HeaderAndFooterRecyclerViewAdapter) outerAdapter).getHeaderViewsCount();
            if (headerViewCounter > 0) {
                return holder.getLayoutPosition() - headerViewCounter;
            }
        }

        return holder.getLayoutPosition();
    }

    /**
     * 请使用本方法替代RecyclerView.ViewHolder的getAdapterPosition()方法
     *
     * @param recyclerView
     * @param holder
     * @return
     */
    public static int getAdapterPosition(RecyclerView recyclerView, RecyclerView.ViewHolder holder) {
        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();
        if (outerAdapter != null && outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter) {

            int headerViewCounter = ((HeaderAndFooterRecyclerViewAdapter) outerAdapter).getHeaderViewsCount();
            if (headerViewCounter > 0) {
                return holder.getAdapterPosition() - headerViewCounter;
            }
        }
        return holder.getAdapterPosition();
    }

    public static int getHeaderCount(RecyclerView recyclerView) {
        int headerCount = 0;
        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();
        if (outerAdapter != null && outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter) {
            headerCount = ((HeaderAndFooterRecyclerViewAdapter) outerAdapter).getHeaderViewsCount();
        }
        return headerCount;
    }

    public static int getFooterCount(RecyclerView recyclerView) {
        int footerCount = 0;
        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();
        if (outerAdapter != null && outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter) {
            footerCount = ((HeaderAndFooterRecyclerViewAdapter) outerAdapter).getFooterViewsCount();
        }
        return footerCount;
    }
}

