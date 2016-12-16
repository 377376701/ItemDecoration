package org.alex.helper;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import org.alex.helper.recycler.LayoutType;

import static org.alex.helper.recycler.LayoutType.GridLayout;
import static org.alex.helper.recycler.LayoutType.HLinearLayout;
import static org.alex.helper.recycler.LayoutType.HStaggeredGridLayout;
import static org.alex.helper.recycler.LayoutType.VLinearLayout;
import static org.alex.helper.recycler.LayoutType.VStaggeredGridLayout;

/**
 * 作者：Alex
 * 时间：2016年12月12日
 * 简述：
 */

public class RecyclerViewHelper {
    protected Builder builder;
    protected RecyclerView recyclerView;

    private RecyclerViewHelper(Builder builder) {
        this.builder = builder;
    }

    public void recyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        layoutManager(builder.layoutType, builder.spanCount);
    }

    private void layoutManager(@LayoutType int layoutType, int spanCount) {
        if (HLinearLayout == layoutType) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
            recyclerView.setLayoutManager(layoutManager);
        } else if (VLinearLayout == layoutType) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(OrientationHelper.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
        } else if (GridLayout == layoutType) {
            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), spanCount);
            layoutManager.setSpanCount(spanCount);
            recyclerView.setLayoutManager(layoutManager);
        } else if (HStaggeredGridLayout == layoutType) {
            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(spanCount, OrientationHelper.HORIZONTAL);
            layoutManager.setSpanCount(spanCount);
            recyclerView.setLayoutManager(layoutManager);
        } else if (VStaggeredGridLayout == layoutType) {
            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(spanCount, OrientationHelper.VERTICAL);
            layoutManager.setSpanCount(spanCount);
            recyclerView.setLayoutManager(layoutManager);
        }
        int paddingFirst = dp2px(builder.paddingFirst);
        int paddingLast = dp2px(builder.paddingLast);
        if (HLinearLayout == builder.layoutType || HStaggeredGridLayout == builder.layoutType) {
            recyclerView.setPadding(paddingFirst, 0, paddingLast, 0);
        } else {
            recyclerView.setPadding(0, paddingFirst, 0, paddingLast);
        }
        recyclerView.setClipToPadding(false);
    }

    /**
     * 数据转换: dp---->px
     */
    private int dp2px(float dp) {
        return (int) (dp * getContext().getResources().getDisplayMetrics().density);
    }

    private Context getContext() {
        return recyclerView.getContext();
    }

    public static final class Builder {
        /**
         * 给 第一行 数据 添加 padding
         * 给 最后一行 数据 添加 padding
         */
        protected int paddingFirst, paddingLast;
        protected int layoutType, spanCount;
        private static Builder instance;

        private Builder() {
        }

        public static Builder getInstance() {
            if (instance == null) {
                synchronized (Builder.class) {
                    instance = instance == null ? new Builder() : instance;
                }
            }
            return instance;
        }

        public Builder paddingFirst(int padding) {
            paddingFirst = padding;
            return this;
        }

        public Builder paddingLast(int padding) {
            paddingLast = padding;
            return this;
        }

        public Builder layoutManager(@LayoutType int layoutType) {
            this.layoutType = layoutType;
            return this;
        }

        public Builder spanCount(int spanCount) {
            this.spanCount = spanCount;
            return this;
        }

        public RecyclerViewHelper build() {
            spanCount = (spanCount <= 0) ? 1 : spanCount;
            return new RecyclerViewHelper(this);
        }

    }


    public static String layoutManager2String(RecyclerView.LayoutManager layoutManager) {
        String result = "线性的 - 垂直";
        if (layoutManager instanceof GridLayoutManager) {
            result = "网格的 " + ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
            result = OrientationHelper.HORIZONTAL == staggeredGridLayoutManager.getOrientation() ? "瀑布流的 - 水平 " : "瀑布流的 - 垂直 ";
            result += staggeredGridLayoutManager.getSpanCount();
        } else {
            result = (OrientationHelper.VERTICAL == ((LinearLayoutManager) layoutManager).getOrientation()) ? "线性的 - 垂直" : "线性的 - 水平";
        }
        return result;
    }

}
