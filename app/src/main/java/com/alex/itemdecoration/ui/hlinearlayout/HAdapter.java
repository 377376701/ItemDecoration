package com.alex.itemdecoration.ui.hlinearlayout;

import com.alex.itemdecoration.R;
import com.chad.adapter.BaseViewHolder;
import com.chad.adapter.SingleRecyclerAdapter;

import org.alex.helper.itemtouchhelper.OnItemMoveListener;

import java.util.Collections;

/**
 * 作者：Alex
 * 时间：2016年12月12日
 * 简述：
 */

public class HAdapter extends SingleRecyclerAdapter<String> implements OnItemMoveListener {
    @Override
    public int getLayoutResId() {
        return R.layout.item_text_h;
    }

    @Override
    protected void onConvert(BaseViewHolder holder, String result) {
        holder.setText(R.id.tv, result);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(list, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }
}
