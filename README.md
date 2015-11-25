##HeaderAndFooterRecyclerView

------

##介绍

HeaderAndFooterRecyclerView是支持addHeaderView、 addFooterView、分页加载的RecyclerView解决方案。

它可以对 RecyclerView 控件进行拓展（通过RecyclerView.Adapter实现），给RecyclerView增加HeaderView、FooterView，并且**不需要**对你的具体业务逻辑Adapter做任何修改。

同时，通过修改 FooterView State，可以动态 FooterView 赋予不同状态（加载中、加载失败、滑到最底等），可以实现 RecyclerView 分页加载数据时的 Loading/TheEnd/NetWorkError 效果。

##使用

* 添加HeaderView、FooterView
```java
        mHeaderAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(mDataAdapter);
        mRecyclerView.setAdapter(mHeaderAndFooterRecyclerViewAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //add a HeaderView
        RecyclerViewUtils.setHeaderView(mRecyclerView, new SampleHeader(this));

        //add a FooterView
        RecyclerViewUtils.setFooterView(mRecyclerView, new SampleFooter(this));
```

* LinearLayout/GridLayout/StaggeredGridLayout布局的RecyclerView分页加载

```java
mRecyclerView.addOnScrollListener(mOnScrollListener);
```

```java
private EndlessRecyclerOnScrollListener mOnScrollListener = new EndlessRecyclerOnScrollListener() {

        @Override
        public void onLoadNextPage(View view) {
            super.onLoadNextPage(view);

            LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(mRecyclerView);
            if(state == LoadingFooter.State.Loading) {
                Log.d("@Cundong", "the state is Loading, just wait..");
                return;
            }

            mCurrentCounter = mDataList.size();

            if (mCurrentCounter < TOTAL_COUNTER) {
                // loading more
                RecyclerViewStateUtils.setFooterViewState(EndlessLinearLayoutActivity.this, mRecyclerView, REQUEST_COUNT, LoadingFooter.State.Loading, null);
                requestData();
            } else {
                //the end
                RecyclerViewStateUtils.setFooterViewState(EndlessLinearLayoutActivity.this, mRecyclerView, REQUEST_COUNT, LoadingFooter.State.TheEnd, null);
            }
        }
    };
```
## 注意事项

如果已经使用 ```RecyclerViewUtils.setHeaderView(mRecyclerView, view);``` 为RecyclerView添加了HeaderView，那么再调用ViewHolder类的```getAdapterPosition()```、```getLayoutPosition()```时返回的值就会因为增加了Header而受影响（返回的position等于真实的position+headerCounter）。

因此，这种情况下请使用
```RecyclerViewUtils.getAdapterPosition(mRecyclerView, ViewHolder.this)```、```RecyclerViewUtils.getLayoutPosition(mRecyclerView, ViewHolder.this)``` 两个方法来替代。

## Demo

* 添加HeaderView、FooterView

![截屏][1]

* 支持分页加载的LinearLayout布局RecyclerView

![截屏][2]

* 支持分页加载的GridLayout布局RecyclerView

![截屏][3]

* 支持分页加载的StaggeredGridLayout布局RecyclerView

![截屏][4]

* 分页加载失败时的GridLayout布局RecyclerView

![截屏][5]