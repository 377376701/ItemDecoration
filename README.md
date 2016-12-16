> ItemDecoration   http://www.jianshu.com/p/befb91411ea5

![预览](https://github.com/Alex-Cin/ItemDecoration/blob/master/preview/003.gif)  ![预览](https://github.com/Alex-Cin/ItemDecoration/blob/master/preview/001.gif)


> 用法

<pre>

public class VActivity extends IDActivity {

    private VAdapter adapter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_v;
    }

    @Override
    public void onCreateData(Bundle bundle) {
        RecyclerView recyclerView = findView(R.id.rv);
        /\*关联 布局类型 \*/
        RecyclerViewHelper.Builder.getInstance()
                .paddingFirst(16)
                .paddingLast(16)
                .layoutManager(LayoutType.VLinearLayout)
                .build()
                .recyclerView(recyclerView);
        /\* 关联 分割线类型 \*/
        recyclerView.addItemDecoration(SimpleItemDecoration.Builder.getInstance()
                .backgroundColor("#EEEEEE")
                .color("#009688")
                .dividerSize(2)
                .marginH(0)
                .marginV(0).build());
        /\*关联 拖动排序\*/
        ItemTouchHelper.Callback callback = DragSwapCallback.Builder.getInstance()
                .longPressDragEnabled(true)
                .dragDismiss(true)
                .onItemDismissListener(new MyOnItemDismissListener()).build();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        adapter = new VAdapter();
        recyclerView.setAdapter(adapter);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("我的下标 = " + i);
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



</pre>
