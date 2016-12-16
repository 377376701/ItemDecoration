package com.alex.itemdecoration.ui.hlinearlayout;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.alex.itemdecoration.R;
import com.alex.itemdecoration.ui.base.IDActivity;

import org.alex.helper.RecyclerViewHelper;
import org.alex.helper.itemdecoration.SimpleItemDecoration;
import org.alex.helper.itemtouchhelper.DragSwapCallback;
import org.alex.helper.itemtouchhelper.OnItemDismissListener;
import org.alex.helper.recycler.LayoutType;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Alex
 * 时间：2016年12月12日
 * 简述：
 * 启动者：
 * ------------{@link com.alex.itemdecoration.ui.MainActivity}
 */

public class HActivity extends IDActivity {

    private HAdapter adapter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_h;
    }

    @Override
    public void onCreateData(Bundle bundle) {
        RecyclerView recyclerView = findView(R.id.rv);
        /*关联 布局类型 */
        RecyclerViewHelper.Builder.getInstance().paddingFirst(16).paddingLast(16).layoutManager(LayoutType.HLinearLayout).build().recyclerView(recyclerView);
        /* 关联 分割线类型 */
        recyclerView.addItemDecoration(SimpleItemDecoration.Builder.getInstance().backgroundColor("#EEEEEE").color("#009688").dividerSize(4).marginV(16).marginH(16).build());
        /*关联 拖动排序*/
        ItemTouchHelper.Callback callback = DragSwapCallback.Builder.getInstance().longPressDragEnabled(true).dragDismiss(true).onItemDismissListener(new MyOnItemDismissListener()).build();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        adapter = new HAdapter();
        recyclerView.setAdapter(adapter);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("下标 = " + i);
        }
        adapter.addItem(list);
    }

    private final class MyOnItemDismissListener implements OnItemDismissListener {
        @Override
        public void onItemDismiss(int position) {
            adapter.removeItem(position);
        }
    }
}
