> ItemDecoration   http://www.jianshu.com/p/befb91411ea5

![预览](https://github.com/Alex-Cin/ItemDecoration/blob/master/pv.png)  ![预览](https://github.com/Alex-Cin/ItemDecoration/blob/master/pg.png)


> 用法

<pre>
@Override
public void onCreateData(Bundle bundle) {
	RecyclerView recyclerView = findView(R.id.rv);
	RecyclerViewHelper.getInstance(recyclerView).layoutManager(LayoutType.VLinearLayout);
	recyclerView.addItemDecoration(new SimpleItemDecoration.Builder().backgroundColor("#EEEEEE")
			.color("#009688").dividerSize(2).marginH(0).marginV(0)
			.paddingFirst(16)
			.paddingFirst(true)
			.paddingLast(16)
			.paddingLast(true).build());
	VAdapter adapter = new VAdapter();
	recyclerView.setAdapter(adapter);
	List<String> list = new ArrayList<>();
	for (int i = 0; i < 10; i++) {
		list.add("我的下标 = " + i);
	}
	adapter.addItem(list);
}
</pre>
