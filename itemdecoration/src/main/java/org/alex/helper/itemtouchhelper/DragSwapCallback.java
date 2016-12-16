package org.alex.helper.itemtouchhelper;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * 作者：Alex
 * 时间：2016/12/16 17 14
 * 简述：
 */

public class DragSwapCallback extends ItemTouchHelper.Callback {
    protected Builder builder;

    private DragSwapCallback(Builder builder) {
        this.builder = builder;
    }

    /**
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = 0;
        int swipeFlags = 0;
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        swipeFlags = builder.isDragDismiss ? ItemTouchHelper.START | ItemTouchHelper.END : 0;
        if (manager instanceof GridLayoutManager || manager instanceof StaggeredGridLayoutManager) {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        } else {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) manager;
            if (linearLayoutManager.getOrientation() == OrientationHelper.HORIZONTAL) {
                dragFlags = ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
                swipeFlags = builder.isDragDismiss ? ItemTouchHelper.UP | ItemTouchHelper.DOWN : 0;
            }
        }
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    /**
     * @param recyclerView
     * @param viewHolder   拖动的ViewHolder
     * @param target       目标位置的ViewHolder
     * @return 返回true表示执行拖动
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if (viewHolder.getItemViewType() != target.getItemViewType()) {
            return false;
        }
        if (recyclerView.getAdapter() instanceof OnItemMoveListener) {
            OnItemMoveListener listener = ((OnItemMoveListener) recyclerView.getAdapter());
            listener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        }
        return true;
    }

    /**
     * @param viewHolder
     * @param direction
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if (builder.onItemDismissListener != null) {
            builder.onItemDismissListener.onItemDismiss(viewHolder.getAdapterPosition());
        }
    }

    /**
     * 长按拖拽功能 手动控制
     *
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return builder.isLongPressDragEnabled;
    }

    public static final class Builder {
        /**
         * 长按 拖动  开启；
         * 滑动删除
         */
        protected boolean isLongPressDragEnabled, isDragDismiss;
        protected OnItemDismissListener onItemDismissListener;
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
        /**
         * 长按拖拽功能 手动控制
         *
         * @param isEnabled
         * @return
         */
        public Builder longPressDragEnabled(boolean isEnabled) {
            this.isLongPressDragEnabled = isEnabled;
            return this;
        }

        /**
         * 滑动删除
         *
         * @param isEnabled
         * @return
         */
        public Builder dragDismiss(boolean isEnabled) {
            this.isDragDismiss = isEnabled;
            return this;
        }

        public Builder onItemDismissListener(OnItemDismissListener onItemDismissListener) {
            this.onItemDismissListener = onItemDismissListener;
            return this;
        }

        public DragSwapCallback build() {
            DragSwapCallback callback = new DragSwapCallback(this);
            return callback;
        }
    }
}
