package com.alex.itemdecoration.ui.gridlayout;

import android.content.Intent;
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
import org.alex.model.ParcelableMap;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Alex
 * 时间：2016/12/13 13 00
 * 简述：
 */

public class GActivity extends IDActivity {
    private int spanCount;
    private GAdapter adapter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_g;
    }

    @Override
    public void onGetIntentData(Intent intent, ParcelableMap parcelableMap) {
        super.onGetIntentData(intent, parcelableMap);
        spanCount = parcelableMap.get("spanCount");
    }

    @Override
    public void onCreateData(Bundle bundle) {
        RecyclerView recyclerView = findView(R.id.rv);
        /*关联 布局类型 */
        RecyclerViewHelper.Builder.getInstance().paddingFirst(16).paddingLast(16).layoutManager(LayoutType.GridLayout).spanCount(spanCount).build().recyclerView(recyclerView);
        /* 关联 分割线类型 */
        recyclerView.addItemDecoration(SimpleItemDecoration.Builder.getInstance().backgroundColor("#EEEEEE").color("#009688").dividerSize(4).marginV(16).marginH(16).build());
        /*关联 拖动排序*/
        ItemTouchHelper.Callback callback = DragSwapCallback.Builder.getInstance().longPressDragEnabled(true).dragDismiss(true).onItemDismissListener(new MyOnItemDismissListener()).build();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        adapter = new GAdapter();
        recyclerView.setAdapter(adapter);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 31; i++) {
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
